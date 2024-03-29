package com.spring_security.test.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class CsrfFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        CsrfToken csrfToken=(CsrfToken)request.getAttribute("_csrf");
        String token=csrfToken.getToken();

        if (csrfToken.getHeaderName() !=null){
            response.setHeader(csrfToken.getHeaderName(),csrfToken.getToken());
        }

        filterChain.doFilter(request,response);
    }
}
