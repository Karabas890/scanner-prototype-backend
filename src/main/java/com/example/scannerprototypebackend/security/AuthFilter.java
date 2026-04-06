package com.example.scannerprototypebackend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    @Value("${app.auth.token}")
    private String token;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header == null || header.isBlank()) {
            sendError(response, "MISSING_TOKEN", "Authorization header is missing");
            return;
        }

        if (!header.startsWith("Bearer ")) {
            sendError(response, "INVALID_FORMAT", "Token must start with Bearer");
            return;
        }

        String actualToken = header.substring(7);

        if (!actualToken.equals(token)) {
            sendError(response, "INVALID_TOKEN", "Token is incorrect");
            return;
        }

        filterChain.doFilter(request, response);
    }

    private void sendError(HttpServletResponse response,
                           String code,
                           String message) throws IOException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        String json = """
                {
                    "error": "%s",
                    "message": "%s"
                }
                """.formatted(code, message);

        response.getWriter().write(json);
    }
}
