package com.spring.jwt;

import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtSecretKey {

    @Autowired
    JwtConfig jwtConfig;

    @Bean
    public SecretKey getSecretKeyForSignIn(){
        return Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
    }
}
