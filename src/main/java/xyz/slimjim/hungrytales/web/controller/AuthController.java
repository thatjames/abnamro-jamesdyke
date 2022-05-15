package xyz.slimjim.hungrytales.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xyz.slimjim.hungrytales.service.api.AuthService;
import xyz.slimjim.hungrytales.web.converter.RegisterRequestDTOConverter;
import xyz.slimjim.hungrytales.web.dto.AuthResponseDTO;
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
    public WebResponse<AuthResponseDTO> register(@RequestBody RegisterRequestDTO registerRequest, HttpServletResponse response) {
        WebResponse<AuthResponseDTO> dtoResponse = new WebResponse<>();
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

}
