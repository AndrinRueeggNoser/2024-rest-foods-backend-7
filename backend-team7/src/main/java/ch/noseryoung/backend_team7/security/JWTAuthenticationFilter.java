package ch.noseryoung.backend_team7.security;

import ch.noseryoung.backend_team7.domain.user.User;
import ch.noseryoung.backend_team7.domain.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.util.Date;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * JWT-Input: Authentication filter for JWT. Must extend from AbstractAuthenticationProcessingFiler.
 * Adjust constructor to fulfill super constructor. Must override following methods: attemptAuthentication(),
 * successfulAuthentication() and unsuccessfulAuthentication()
 */
class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    private final JWTProperties jwtProperties;

    JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                            AntPathRequestMatcher antPathRequestMatcher,
                            JWTProperties properties) {
        super(antPathRequestMatcher,authenticationManager);

        this.jwtProperties = properties;
    }

    /**
     * JWT-Input: Defines login attempt. Prepares received login credentials for authentication
     *
     * @param req from which to extract parameters and perform the authentication
     * @param res the response, which may be needed if the implementation has to do a
     * redirect as part of a multi-stage authentication process (such as OIDC).
     * @return
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) {
        try {
            User credentials = new ObjectMapper().readValue(req.getInputStream(), User.class);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(credentials.getEmail(), credentials.getPassword()));
        }
        catch (IOException e) {
            return null;
        }
    }

    /**
     * JWT-Input: Method for building JWT and returning User in response body on successful
     * authentication.
     *
     * @param req
     * @param res
     * @param chain
     * @param auth the object returned from the <tt>attemptAuthentication</tt>
     * method.
     * @throws IOException
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                            Authentication auth) throws IOException {
        // JWT-Input: Adds the UserDetailsImpl logic to the authenticated user
        UserService.UserDetailsImpl userDetailsImpl = (UserService.UserDetailsImpl) auth.getPrincipal();
        User user = userDetailsImpl.user();
        Integer subject = user.getUserId();

        // JWT-Input: calculates expiration date with expiration duration
        Date expirationTimestamp = new Date(System.currentTimeMillis() + jwtProperties.getExpirationTime());

        // JWT-Input: Builds the JWT
        String token = Jwts.builder()
                .setSubject(subject.toString())
                .setId(subject.toString())
                .setIssuer(jwtProperties.getIssuer())
                .setAudience(jwtProperties.getAudience())
                .setExpiration(expirationTimestamp)
                .signWith(SignatureAlgorithm.HS512, jwtProperties.getSecret().getBytes())
                .compact();

        // JWT-Input: Creates entry with JWT in header and exposes new header entry
        res.addHeader(jwtProperties.getHeaderString(), jwtProperties.getTokenPrefix() + " " + token);
        // Expose the Headers
        res.addHeader("Access-Control-Expose-Headers", jwtProperties.getHeaderString());

        // JWT-Input: Put the user's ID and roles into the response body
        String userString = new ObjectMapper().writeValueAsString(user);
        res.getWriter().write(userString);

        res.setContentType("application/json");
    }

    /**
     * Returns UNAUTHORIZED status in response on unsuccessful authentication
     *
     * @param request
     * @param response
     * @param failed
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                              AuthenticationException failed) {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

}

