package com.example.banking.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthFilter extends GenericFilter {
    private final JwtService jwtService;

    public JwtAuthFilter(JwtService jwtService){ this.jwtService = jwtService; }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        String auth = req.getHeader("Authorization");
        if(auth != null && auth.startsWith("Bearer ")){
            try{
                var jws = jwtService.parse(auth.substring(7));
                Claims c = jws.getBody();
                var authToken = new UsernamePasswordAuthenticationToken(
                        c.getSubject(), null, List.of());
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }catch(Exception ignored){}
        }
        chain.doFilter(request, response);
    }
}
