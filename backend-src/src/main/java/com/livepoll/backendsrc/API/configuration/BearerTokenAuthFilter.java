package com.livepoll.backendsrc.API.configuration;

import com.livepoll.backendsrc.API.entity.jpa.UserEntity;
import com.livepoll.backendsrc.API.exception.customExceptions.ExpiredTokenException;
import com.livepoll.backendsrc.API.exception.customExceptions.ParseTokenException;
import com.livepoll.backendsrc.API.service.tokens.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;

@Component
public class BearerTokenAuthFilter extends OncePerRequestFilter {

    TokenService tokenService;
    HandlerExceptionResolver handlerExceptionResolver;

    public BearerTokenAuthFilter(TokenService tokenService, HandlerExceptionResolver handlerExceptionResolver) {
        this.tokenService = tokenService;
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Override
    protected  void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //Getting accesToken String
        String accessToken = tokenService.parseTokenFromHeader(request.getHeader("Authorization"));

        //Check if accessToken is valid
        if (!accessToken.isEmpty()){
            try {
                Optional<UserEntity> userEntity = tokenService.validateJWTToken(accessToken);
                if(userEntity.isEmpty()){
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                }else {
                    System.out.println("User was found");
                    UserEntity user = userEntity.get();
                    Authentication authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(),null, new ArrayList<>());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }catch (ParseTokenException | ExpiredTokenException e) {
                handlerExceptionResolver.resolveException(request, response, null, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }
}
