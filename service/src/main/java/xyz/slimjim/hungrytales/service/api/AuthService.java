package xyz.slimjim.hungrytales.service.api;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.auth.LoginRequest;
import xyz.slimjim.hungrytales.common.auth.RegisterRequest;
import xyz.slimjim.hungrytales.common.auth.User;
import xyz.slimjim.hungrytales.service.auth.AuthToken;

@Component
public interface AuthService {

    void register(RegisterRequest registerRequest);

    User login(LoginRequest loginRequest);

    boolean validateToken(String token);

    String createToken(User user);

    String refreshToken(String token);

    AuthToken parseToken(String token);
}
