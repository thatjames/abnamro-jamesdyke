package xyz.slimjim.hungrytales.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.common.exceptions.AuthException;
import xyz.slimjim.hungrytales.service.api.AuthService;
import xyz.slimjim.hungrytales.service.auth.AuthToken;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class TokenFilter implements Filter {

    @Autowired
    private AuthService authService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
            if (httpServletRequest.getRequestURI().startsWith("/auth")) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }

            String claim = httpServletRequest.getHeader("Authorization");
            if (claim == null || !claim.contains(" ")) {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            String authToken = claim.split(" ")[1];
            AuthToken tokenData = authService.parseToken(authToken);
            if (!tokenData.isTTLValid()) {
                httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }

            httpServletRequest.setAttribute("auth_object", tokenData);
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (AuthException ax) {
            ((HttpServletResponse) servletResponse).setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
