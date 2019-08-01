package com.moraes.agenda.config.security;

import com.moraes.agenda.exceptions.TokenInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class JwtAuthenticationFilter extends GenericFilterBean {

    private TokenAuthenticationService service;

    public JwtAuthenticationFilter(TokenAuthenticationService service) {
        this.service = service;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        String token = service.resolveToken((HttpServletRequest) request);
        Authentication auth = null;
        try {
            if (token != null && service.validateToken(token)) {
                auth = service.getAuthentication(token);
                SecurityContextHolder.getContext().setAuthentication(auth);
            } else {
                throw new TokenInvalidException("Token Inválido");
            }

        } catch (Exception e) {
            SecurityContextHolder.clearContext();
        }

        filterChain.doFilter(request, response);
    }

}
