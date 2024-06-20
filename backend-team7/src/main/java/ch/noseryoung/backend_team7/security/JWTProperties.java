package ch.noseryoung.backend_team7.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
//Takes all A.P that start with jwt.
@Configuration("jwt")
public class JWTProperties {
    //Das geht auch
     @Value("jwt.expirationTime")
    private long expirationTime
}
