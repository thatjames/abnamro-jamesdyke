package xyz.slimjim.hungrytales.service.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.bouncycastle.crypto.AsymmetricBlockCipher;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.InvalidCipherTextException;
import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.engines.RSAEngine;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.generators.RSAKeyPairGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.params.RSAKeyGenerationParameters;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.auth.LoginRequest;
import xyz.slimjim.hungrytales.common.auth.RegisterRequest;
import xyz.slimjim.hungrytales.common.auth.User;
import xyz.slimjim.hungrytales.common.exceptions.AuthException;
import xyz.slimjim.hungrytales.common.exceptions.HungryTalesException;
import xyz.slimjim.hungrytales.common.exceptions.LoginFailedException;
import xyz.slimjim.hungrytales.service.api.AuthService;
import xyz.slimjim.hungrytales.storage.service.AuthDAO;

import java.io.IOException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private static final int ITERATIONS = 2000;
    private static final int SALT_SIZE = 512;
    private static final int KEY_SIZE = 4096;

    private static final DigestRandomGenerator RANDOM_GENERATOR = new DigestRandomGenerator(new SHA256Digest());
    private static final PKCS5S2ParametersGenerator PARAMS_GENERATOR = new PKCS5S2ParametersGenerator();
    private static final AsymmetricCipherKeyPair KEY_PAIR;

    private Queue<String> burnedTokens;

    static {
        Security.addProvider(new BouncyCastleProvider());
        try {
            RSAKeyPairGenerator generator = new RSAKeyPairGenerator();
            generator.init(new RSAKeyGenerationParameters(new BigInteger("1001", 16), SecureRandom.getInstance("SHA1PRNG"), 2048, 80));
            log.info("Creating keypair, this can take a while");
            KEY_PAIR = generator.generateKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            throw new HungryTalesException("Unable to create key pair generator", ex);
        }
    }

    public AuthServiceImpl() {
        burnedTokens = new ConcurrentLinkedQueue<>();
    }

    @Autowired
    private AuthDAO authDAO;

    @Override
    public void register(RegisterRequest registerRequest) {
        registerRequest.setSalt(salt());
        registerRequest.setPassword(encryptPassword(registerRequest.getChallenge(), registerRequest.getSalt()));
        authDAO.register(registerRequest);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        User user = authDAO.getUser(loginRequest);
        if (!validatePassword(loginRequest.getChallenge(), user.getSalt(), user.getPassword())) {
            throw new LoginFailedException("username/password was incorrect");
        }
        user.setPassword(null);
        user.setSalt(null);
        return user;
    }

    @Override
    public boolean validateToken(String token) {
        AuthToken authToken = parseToken(token);
        return authToken.isTTLValid();
    }

    @Override
    public AuthToken parseToken(String token) {
        if (burnedTokens.contains(token)) {
            throw new AuthException("burned token");
        }
        try {
            byte[] claim = Base64.getDecoder().decode(token);
            AsymmetricBlockCipher engine = new RSAEngine();
            engine.init(false, KEY_PAIR.getPrivate());
            return new ObjectMapper().registerModule(new JavaTimeModule()).readValue(engine.processBlock(claim, 0, claim.length), AuthToken.class);
        } catch (IOException | InvalidCipherTextException ex) {
            throw new AuthException("access denied");
        }
    }

    @Override
    public String createToken(User user) {
        try {
            byte[] jsonData = new ObjectMapper().registerModule(new JavaTimeModule()).writeValueAsBytes(new AuthToken(user));
            AsymmetricBlockCipher engine = new RSAEngine();
            engine.init(true, KEY_PAIR.getPublic());
            return Base64.getEncoder().encodeToString(engine.processBlock(jsonData, 0, jsonData.length));
        } catch (JsonProcessingException | InvalidCipherTextException ex) {
            throw new HungryTalesException("Unable to create secure token", ex);
        }
    }

    @Override
    public String refreshToken(String token) {
        try {
            AuthToken authToken = parseToken(token);
            burnToken(token);
            return createToken(authToken.getUser());
        } catch (Exception ex) {
            throw new HungryTalesException("Unable to refresh token", ex);
        }
    }

    private byte[] encryptPassword(String plainText, byte[] salt) {
        PARAMS_GENERATOR.init(PBEParametersGenerator.PKCS5PasswordToBytes(plainText.toCharArray()), salt, ITERATIONS);
        return ((KeyParameter) PARAMS_GENERATOR.generateDerivedParameters(KEY_SIZE)).getKey();
    }

    private byte[] salt() {
        byte[] salt = new byte[AuthServiceImpl.SALT_SIZE];
        RANDOM_GENERATOR.nextBytes(salt);
        return salt;
    }

    private boolean validatePassword(String plainPassword, byte[] salt, byte[] hash) {
        return Arrays.equals(encryptPassword(plainPassword, salt), hash);
    }

    private void burnToken(String burnedToken) {
        burnedTokens.add(burnedToken);
        if (burnedTokens.size() > 100) {
            burnedTokens.remove();
        }
    }
}
