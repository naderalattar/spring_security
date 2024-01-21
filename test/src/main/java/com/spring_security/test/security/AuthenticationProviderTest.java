package com.spring_security.test.security;


import com.spring_security.test.model.Users;
import com.spring_security.test.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class AuthenticationProviderTest implements AuthenticationProvider {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username=(String)authentication.getPrincipal();
        String password=(String)authentication.getCredentials();

        if(userRepo.findById(username).isPresent()){

            Users user=userRepo.findById(username).get();

            if(!passwordEncoder.matches(password,user.getPassword()))
                throw new BadCredentialsException("Wrong username or password");

            List<SimpleGrantedAuthority> simpleGrantedAuthorities=user.getAuthorities()
                    .stream().map(authority -> new SimpleGrantedAuthority(authority.getAuthority()) )
                    .collect(Collectors.toList());
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=
                    new UsernamePasswordAuthenticationToken(username,password,simpleGrantedAuthorities);
            return usernamePasswordAuthenticationToken;

        }
        else {
            throw new BadCredentialsException("Wrong username or password");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {

        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
