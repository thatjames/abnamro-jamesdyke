package xyz.slimjim.hungrytales.web.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

public class AuthDTO extends BaseDTO {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private UserDTO userDTO;
    private String authToken;

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }
}
