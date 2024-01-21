package com.spring_security.test.controller;

import com.spring_security.test.model.Users;
import com.spring_security.test.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpRequest;

@RestController
//@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${secret.key}")
    String secretKey;

    @GetMapping("/user")
    public Users getUsers(Authentication authentication){

        String username=authentication.getPrincipal().toString();

        return userRepo.findById(username).get();
    }

    @PostMapping("/user")
    public Users persistUser(@RequestBody Users users){
        users.setPassword(passwordEncoder.encode(users.getPassword()));
        Users savedUsers=userRepo.save(users);

        return savedUsers;
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String username){
        userRepo.deleteById(username);

    }



}
