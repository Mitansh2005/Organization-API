package com.task.org.config;

import com.task.org.service.CustomUserDetailsService;
import com.task.org.service.JWTService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Component
public class JwtFilter extends OncePerRequestFilter {
    private final JWTService jwtService;
    private final CustomUserDetailsService customUserDetailsService;
    private final HandlerExceptionResolver exceptionResolver;
    public JwtFilter(JWTService jwtService, CustomUserDetailsService customUserDetailsService,@Qualifier("handlerExceptionResolver")HandlerExceptionResolver exceptionResolver) {
        this.jwtService = jwtService;
        this.customUserDetailsService = customUserDetailsService;
        this.exceptionResolver=exceptionResolver;
    }
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
    {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                token = authHeader.substring(7);
                try {
                username = jwtService.extractUsername(token);
                }catch (ExpiredJwtException ex){
                    exceptionResolver.resolveException(request,response,null,ex);
                }
            }
        try {
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                if (jwtService.validateToken(token, userDetails)) {
                    UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    userToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(userToken);
                }
            }
            filterChain.doFilter(request, response);
        }catch (Exception ex){
            exceptionResolver.resolveException(request,response,null,ex);
        }
    }
}
