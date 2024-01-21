package com.spring_security.test.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {
    @Value("${secret.key}")
  public  String secretKey;
}
