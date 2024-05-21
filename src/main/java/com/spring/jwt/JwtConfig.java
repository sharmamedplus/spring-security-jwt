package com.spring.jwt;

import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;

import javax.crypto.SecretKey;

@ConfigurationProperties(prefix = "application.jwt" )
@Data
@Setter
@Getter
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExpirationAfterDays;



    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }
}
