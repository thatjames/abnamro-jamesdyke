package xyz.slimjim.hungrytales.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.slimjim.hungrytales.common.auth.User;
import xyz.slimjim.hungrytales.service.api.AuthService;
import xyz.slimjim.hungrytales.web.converter.LoginRequestDTOConverter;
import xyz.slimjim.hungrytales.web.converter.RegisterRequestDTOConverter;
import xyz.slimjim.hungrytales.web.converter.UserItemDTOConverter;
import xyz.slimjim.hungrytales.web.dto.LoginRequestDTO;
import xyz.slimjim.hungrytales.web.dto.LoginResponseDTO;
import xyz.slimjim.hungrytales.web.dto.RegisterRequestDTO;
import xyz.slimjim.hungrytales.web.response.WebResponse;

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
    public WebResponse<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest, HttpServletResponse response) {
        WebResponse<LoginResponseDTO> dtoResponse = new WebResponse<>();
        log.info(String.format("login attempt for %s", loginRequest.getUsername()));
        try {
            LoginRequestDTOConverter converter = new LoginRequestDTOConverter();
            User user = authService.login(converter.fromDTOToItem(loginRequest));
            UserItemDTOConverter userConverter = new UserItemDTOConverter();
            LoginResponseDTO responseDTO = new LoginResponseDTO();
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
}
