package com.spring_security.test.filter;

import com.spring_security.test.security.Constants;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

//@Component

public class JWTTokenGenerator extends OncePerRequestFilter {

    @Value("${secret.key}")
    private String secretKey;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null){

            SecretKey key= Keys.hmacShaKeyFor(new Constants().secretKey.getBytes());
            System.out.println(new Constants().secretKey+"?????");

            String jwt= Jwts.builder().issuer("Alattra")
                    .claim("username",authentication.getName())
                    .claim("authorities",populateAuthorities(authentication.getAuthorities()))
                    .issuedAt(new Date())
                    .expiration(new Date(new Date().getTime()+300000000))
                    .signWith(key)
                    .compact();

            response.setHeader("Authorization",jwt);
        }
        filterChain.doFilter(request,response);
    }

    private String populateAuthorities(Collection<? extends GrantedAuthority> collection) {
        Set<String> authoritiesSet = new HashSet<>();
        for (GrantedAuthority authority : collection) {
            authoritiesSet.add(authority.getAuthority());
        }
        return String.join(",", authoritiesSet);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return ! request.getServletPath().equals("/user");
    }

  
}

