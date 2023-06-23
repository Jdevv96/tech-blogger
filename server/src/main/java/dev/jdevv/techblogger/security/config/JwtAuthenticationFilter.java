package dev.jdevv.techblogger.security.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    /*
        * Intercepts HTTP request runs on every request
        * Validates and checks token information
        * Handles token validation response
     */

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request, // Request
            @NonNull HttpServletResponse response, // Can intercept request and provide customer details (header) in response
            @NonNull FilterChain filterChain // List of filters to execute
    ) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization"); // Stores request header
        final String jwt;
        final String userName;

        // Checks against null or invalid auth header
        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response); // Invokes next chain w response and request to pass
            return;
        }

        // Extracts JWT
        jwt = authHeader.substring(7);
        // Extract userName
        userName = jwtService.extractUserName(jwt);
        // Check if we have username in db and user is not authenticated
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(userName);
            // Use userDetails to verify validity of user
            if (jwtService.isTokenValid(jwt, userDetails)) {
                // Create userName and password auth token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // Update authentication token
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        // Call for next chain in filter to be executed
        filterChain.doFilter(request, response);
    }
}
