package org.example.jwtnewversion.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.jwtnewversion.service.JWTUtils;
import org.example.jwtnewversion.service.OurUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthFilter extends OncePerRequestFilter {

    private JWTUtils jwtUtils;

    private OurUserDetailsService ourUserDetailsService;

    public AuthFilter(JWTUtils jwtUtils, OurUserDetailsService ourUserDetailsService) {
        this.jwtUtils = jwtUtils;
        this.ourUserDetailsService = ourUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("Authorization");

        if (header != null && header.contains("Bearer")) {
            final String token = header.substring(7);
            final String username = jwtUtils.getUsernameToken(token);
        }

        filterChain.doFilter(request, response);
    }
}
