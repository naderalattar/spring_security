package com.spring_security.test.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Users {
    @Id
    @Column(name = "username")
    String username;

    @Column(name = "password")
    String password;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities",joinColumns = @JoinColumn(name = "username")
                                       ,inverseJoinColumns = @JoinColumn(name = "authority"))
    List<Authorities> authorities;



}
