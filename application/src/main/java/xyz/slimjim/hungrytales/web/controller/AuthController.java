package xyz.slimjim.hungrytales.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.slimjim.hungrytales.common.auth.User;
import xyz.slimjim.hungrytales.service.api.AuthService;
import xyz.slimjim.hungrytales.web.converter.LoginRequestDTOConverter;
import xyz.slimjim.hungrytales.web.converter.RegisterRequestDTOConverter;
import xyz.slimjim.hungrytales.web.converter.UserItemDTOConverter;
import xyz.slimjim.hungrytales.web.dto.AuthDTO;
import xyz.slimjim.hungrytales.web.dto.LoginRequestDTO;
import xyz.slimjim.hungrytales.web.dto.RegisterRequestDTO;
import xyz.slimjim.hungrytales.web.response.WebResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public WebResponse<Void> register(@RequestBody RegisterRequestDTO registerRequest, HttpServletResponse response) {
        WebResponse<Void> dtoResponse = new WebResponse<>();
        log.info(String.format("Register user %s", registerRequest.getUsername()));
        try {
            RegisterRequestDTOConverter converter = new RegisterRequestDTOConverter();
            authService.register(converter.fromDTOToItem(registerRequest));
            dtoResponse.setResult(true);
        } catch (Exception ex) {
            log.error("Unable to register user", ex);
            dtoResponse.setErrorMessage(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return dtoResponse;
    }

    @PostMapping("/login")
    public WebResponse<AuthDTO> login(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
        WebResponse<AuthDTO> dtoResponse = new WebResponse<>();
        log.info(String.format("login attempt for %s", loginRequest.getUsername()));
        try {
            LoginRequestDTOConverter converter = new LoginRequestDTOConverter();
            User user = authService.login(converter.fromDTOToItem(loginRequest));
            UserItemDTOConverter userConverter = new UserItemDTOConverter();
            AuthDTO responseDTO = new AuthDTO();
            responseDTO.setUserDTO(userConverter.fromItemToDTO(user));
            responseDTO.setAuthToken(authService.createToken(user));
            dtoResponse.setData(responseDTO);
            dtoResponse.setResult(true);
        } catch (Exception ex) {
            log.error("Unable to process login", ex);
            dtoResponse.setErrorMessage(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
        return dtoResponse;
    }

    @PostMapping("/refresh")
    public WebResponse<AuthDTO> refresh(HttpServletRequest request, HttpServletResponse response) {
        WebResponse<AuthDTO> dtoResponse = new WebResponse<>();
        log.info("refresh token called");
        try {
            String authBearer = request.getHeader("Authorization").split(" ")[1];
            String newToken = authService.refreshToken(authBearer);
            AuthDTO tokenData = new AuthDTO();
            tokenData.setAuthToken(newToken);
            dtoResponse.setResult(true);
            dtoResponse.setData(tokenData);
        } catch (Exception ex) {
            log.error("Unable to refresh token", ex);
            dtoResponse.setErrorMessage(ex.getMessage());
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        return dtoResponse;
    }
}
