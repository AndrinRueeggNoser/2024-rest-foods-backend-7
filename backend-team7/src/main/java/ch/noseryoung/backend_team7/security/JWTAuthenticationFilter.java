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

    public static final String HEADER_STRING = "Authorization";
    public static final String ISS = "SB2Demo";
    public static final String AUD = "SB2Demo";
    public static final String SECRET = "SecretKeyToGenJWTs";

    public static final String PREFIX = "Bearer";

    public static final int EXP_TIME = 28_800_000;

    JWTAuthenticationFilter(AuthenticationManager authenticationManager,
                            AntPathRequestMatcher antPathRequestMatcher) {
        super(antPathRequestMatcher,authenticationManager);
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
        System.out.println("Attempting login");
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
        System.out.println("Successful login");
        UserService.UserDetailsImpl userDetailsImpl = (UserService.UserDetailsImpl) auth.getPrincipal();
        User user = userDetailsImpl.user();
        Integer subject = user.getUserId();

        // JWT-Input: calculates expiration date with expiration duration
        Date expirationTimestamp = new Date(System.currentTimeMillis() + EXP_TIME);

        // JWT-Input: Builds the JWT
        String token = Jwts.builder()
                .setSubject(subject.toString())
                .setId(subject.toString())
                .setIssuer(ISS)
                .setAudience(AUD)
                .setExpiration(expirationTimestamp)
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();

        // JWT-Input: Creates entry with JWT in header and exposes new header entry
        res.addHeader(HEADER_STRING, PREFIX + " " + token);
        // Expose the Headers
        res.addHeader("Access-Control-Expose-Headers", HEADER_STRING);

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
        System.out.println("Login incorrect");
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
    }

}

