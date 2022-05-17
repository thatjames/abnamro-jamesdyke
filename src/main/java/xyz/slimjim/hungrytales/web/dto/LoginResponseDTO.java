package xyz.slimjim.hungrytales.web.dto;

public class LoginResponseDTO extends BaseDTO {

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
