package com.example.security.jwt;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "application.jwt")
@NoArgsConstructor
@Getter
@Setter
public class JwtConfig {
    private String secretKey;
    private String tokenPrefix;
    private Integer tokenExprirationAfterDays;



    public String getAuthorizationHeader(){
        return HttpHeaders.AUTHORIZATION;
    }
}
