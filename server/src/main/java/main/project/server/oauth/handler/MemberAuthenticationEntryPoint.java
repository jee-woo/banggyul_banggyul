package main.project.server.oauth.handler;

import lombok.extern.slf4j.Slf4j;
import main.project.server.oauth.utils.OAuth2ErrorResponder;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class MemberAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        Exception exception = (Exception) request.getAttribute("exception");
        OAuth2ErrorResponder.sendErrorResponse(response, HttpStatus.UNAUTHORIZED);
        log.warn("Unauthorized error: {}", exception.getMessage());
    }
}
