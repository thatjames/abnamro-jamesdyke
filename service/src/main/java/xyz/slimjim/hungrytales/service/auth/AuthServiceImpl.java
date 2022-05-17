package xyz.slimjim.hungrytales.service.auth;

import org.bouncycastle.crypto.PBEParametersGenerator;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.generators.PKCS5S2ParametersGenerator;
import org.bouncycastle.crypto.params.KeyParameter;
import org.bouncycastle.crypto.prng.DigestRandomGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.slimjim.hungrytales.common.auth.LoginRequest;
import xyz.slimjim.hungrytales.common.auth.RegisterRequest;
import xyz.slimjim.hungrytales.common.auth.User;
import xyz.slimjim.hungrytales.common.exceptions.HungryTalesException;
import xyz.slimjim.hungrytales.service.api.AuthService;
import xyz.slimjim.hungrytales.storage.service.AuthDAO;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class AuthServiceImpl implements AuthService {

    private static final int ITERATIONS = 2000;
    private static final int SALT_SIZE = 512;
    private static final int KEY_SIZE = 4096;

    private static final DigestRandomGenerator RANDOM_GENERATOR = new DigestRandomGenerator(new SHA256Digest());
    private static final PKCS5S2ParametersGenerator PARAMS_GENERATOR = new PKCS5S2ParametersGenerator();

    @Autowired
    private AuthDAO authDAO;

    @Override
    public void register(RegisterRequest registerRequest) {
        registerRequest.setSalt(salt(SALT_SIZE));
        registerRequest.setPassword(encryptPassword(registerRequest.getChallenge(), registerRequest.getSalt()));
        authDAO.register(registerRequest);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        return authDAO.login(loginRequest);
    }

    private byte[] encryptPassword(String plainText, byte[] salt) {
        PARAMS_GENERATOR.init(PBEParametersGenerator.PKCS5PasswordToBytes(plainText.toCharArray()), salt, ITERATIONS);
        return ((KeyParameter) PARAMS_GENERATOR.generateDerivedParameters(KEY_SIZE)).getKey();
    }

    private byte[] salt(int size) {
        byte[] salt = new byte[size];
        RANDOM_GENERATOR.nextBytes(salt);
        return salt;
    }

}
