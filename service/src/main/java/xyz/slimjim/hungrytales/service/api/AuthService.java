package xyz.slimjim.hungrytales.service.api;

import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.auth.LoginRequest;
import xyz.slimjim.hungrytales.common.auth.RegisterRequest;
import xyz.slimjim.hungrytales.common.auth.User;

@Component
public interface AuthService {

    void register(RegisterRequest registerRequest);

    User login(LoginRequest loginRequest);

    boolean validateToken(String token);

    String createToken(User user);
}
