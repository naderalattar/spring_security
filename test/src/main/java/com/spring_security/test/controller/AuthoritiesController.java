package com.spring_security.test.controller;

import com.spring_security.test.model.Authorities;
import com.spring_security.test.repository.AuthoritiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthoritiesController {

    @Autowired
    AuthoritiesRepo authoritiesRepo;

    @DeleteMapping("/authority")
    public void delete(@RequestParam String authority){
        authoritiesRepo.deleteById(authority);
    }

    @PostMapping("/authority")
    public Authorities add(@RequestBody Authorities authorities){

        Authorities savedAuthorities=authoritiesRepo.save(authorities);
        return savedAuthorities;
    }
}
