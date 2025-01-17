package com.example.utils;

import com.example.Service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //The Authorization header is extracted from the HTTP request.This header typically contains the JWT in the format: Bearer <token>.
        String authorizationHeader = request.getHeader("Authorization");
        String username = null;
        String jwt = null;

        //Extract the JWT and Username
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){
            jwt = authorizationHeader.substring(7);
            try {
            	username = jwtUtil.extractUsername(jwt);
            	System.out.println("JWT Token: " + jwt);
            } catch (Exception e){
            	// Handle cases where the JWT is malformed or invalid
                System.out.println("Invalid JWT: " + e.getMessage());
            }
            
        } else if (authorizationHeader == null) {
            System.out.println("Missing Authorization header");
        } else {
            System.out.println("Invalid Authorization header format");
        }

        //Validate the JWT and Authenticate the User
        if(username != null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            if(jwtUtil.validateToken(jwt)) {
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        filterChain.doFilter(request, response);
    }
}
