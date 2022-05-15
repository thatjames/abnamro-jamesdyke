package xyz.slimjim.hungrytales.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import xyz.slimjim.hungrytales.common.auth.LoginRequest;
import xyz.slimjim.hungrytales.common.auth.RegisterRequest;
import xyz.slimjim.hungrytales.common.auth.User;
import xyz.slimjim.hungrytales.service.api.AuthService;
import xyz.slimjim.hungrytales.storage.service.AuthDAO;

public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthDAO authDAO;

    @Override
    public void register(RegisterRequest registerRequest) {
        authDAO.register(registerRequest);
    }

    @Override
    public User login(LoginRequest loginRequest) {
        return authDAO.login(loginRequest);
    }
}
