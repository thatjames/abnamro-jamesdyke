package xyz.slimjim.hungrytales.web.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import xyz.slimjim.hungrytales.service.api.AuthService;

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
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (httpServletRequest.getRequestURI().startsWith("/auth")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        String claim = httpServletRequest.getHeader("Authorization");
        if (!claim.contains(" ")) {
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        String authToken = claim.split(" ")[1];
    }
}
