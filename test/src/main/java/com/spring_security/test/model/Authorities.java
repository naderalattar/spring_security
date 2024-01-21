package com.spring_security.test.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Authorities {

    @Id
    String authority;

    @JsonIgnore
    @ManyToMany
            @JoinTable(name = "users_authorities",
                    joinColumns = @JoinColumn(name = "authority"),
                    inverseJoinColumns = @JoinColumn(name = "username")
            )
    List<Users> users;
}
