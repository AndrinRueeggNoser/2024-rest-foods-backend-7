package ch.noseryoung.backend_team7.security;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * JWT-Input: Class for holding values from application.properties. Contains fields, constructor,
 * getter and setter
 */
@Component
@ConfigurationProperties("jwt") // JWT-Input: defines the prefix of the values in application.properties
public class JWTProperties {
    private long expirationTime;
    private String secret;
    private String tokenPrefix;
    private String headerString;
    private String issuer;
    private String audience;

    public JWTProperties() {
        this.expirationTime = 0L;
        this.secret = "";
        this.tokenPrefix = "";
        this.headerString = "";
        this.issuer = "";
        this.audience = "";
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public void setTokenPrefix(String tokenPrefix) {
        this.tokenPrefix = tokenPrefix;
    }

    public String getHeaderString() {
        return headerString;
    }

    public void setHeaderString(String headerString) {
        this.headerString = headerString;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
}
