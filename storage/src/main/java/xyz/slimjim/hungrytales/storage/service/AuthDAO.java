package xyz.slimjim.hungrytales.storage.service;

import xyz.slimjim.hungrytales.common.auth.LoginRequest;
import xyz.slimjim.hungrytales.common.auth.RegisterRequest;
import xyz.slimjim.hungrytales.common.auth.User;

public interface AuthDAO {

    void register(RegisterRequest registerRequest);

    User getUser(LoginRequest loginRequest);
}
