package xyz.slimjim.hungrytales.web.converter;

import xyz.slimjim.hungrytales.common.auth.LoginRequest;
import xyz.slimjim.hungrytales.web.dto.LoginRequestDTO;

public class LoginRequestDTOConverter implements DataObjectDTOConverter<LoginRequest, LoginRequestDTO> {
    @Override
    public LoginRequestDTO fromItemToDTO(LoginRequest item) {
        throw new UnsupportedOperationException("impossible to convert");
    }

    @Override
    public LoginRequest fromDTOToItem(LoginRequestDTO dto) {
        LoginRequest request = new LoginRequest();
        request.setUsername(dto.getUsername());
        request.setChallenge(dto.getPassword());
        return request;
    }
}
