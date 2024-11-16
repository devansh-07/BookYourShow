package com.BookYourShow.UserManagement.Filter;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.BookYourShow.UserManagement.Utils.JwtUtil;
import com.auth0.jwt.interfaces.DecodedJWT;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
public class JwtRequestFilter implements Filter {

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtRequestFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String requestURI = httpServletRequest.getRequestURI();

        System.out.println("requestURI: " + requestURI);

        if (requestURI.equals("/api/users/verify")) {
            String authHeader = httpServletRequest.getHeader("Authorization");

            if ((authHeader == null) || (!authHeader.startsWith("Bearer "))) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized.");
                return;
            }
            
            // Validate the JWT token
            String authToken = authHeader.substring(7);
            DecodedJWT decodedJWT = jwtUtil.decodeToken(authToken);
            
            if (decodedJWT == null) {
                httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token.");
                return;
            }

            httpServletRequest.setAttribute("email", jwtUtil.extractEmail(decodedJWT));
        }

        chain.doFilter(request, response);
    }

}
