package com.spring_security.test.repository;

import com.spring_security.test.model.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepo extends JpaRepository<Authorities,String> {

}
