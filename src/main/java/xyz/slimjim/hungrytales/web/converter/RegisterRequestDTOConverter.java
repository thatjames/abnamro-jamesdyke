package xyz.slimjim.hungrytales.web.converter;

import xyz.slimjim.hungrytales.common.auth.RegisterRequest;
import xyz.slimjim.hungrytales.web.dto.RegisterRequestDTO;

public class RegisterRequestDTOConverter implements DataObjectDTOConverter<RegisterRequest, RegisterRequestDTO> {
    @Override
    public RegisterRequestDTO fromItemToDTO(RegisterRequest item) {
        return null;
    }

    @Override
    public RegisterRequest fromDTOToItem(RegisterRequestDTO dto) {
        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setChallenge(dto.getPassword());
        registerRequest.setName(dto.getName());
        registerRequest.setSurname(dto.getSurname());
        registerRequest.setUsername(dto.getUsername());
        return registerRequest;
    }
}
