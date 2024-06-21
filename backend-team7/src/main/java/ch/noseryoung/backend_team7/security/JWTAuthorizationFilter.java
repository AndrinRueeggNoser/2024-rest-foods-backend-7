package ch.noseryoung.backend_team7.security;
import ch.noseryoung.backend_team7.domain.user.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * JWT-Input: Authorization filter for JWT. Must extend from OncePerRequestFilter.
 * Must override following methods: doFilterInternal()
 */
class JWTAuthorizationFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final JWTProperties jwtProperties;

    JWTAuthorizationFilter(UserService userService, JWTProperties jwtProperties) {
        this.userService = userService;
        this.jwtProperties = jwtProperties;
    }

    /**
     * Get the token from the authorization-header or the defined query-parameter and set the principal
     *
     * @param req   request that is being processed
     * @param res   response of the request
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        String authorizationHeader = req.getHeader(jwtProperties.getHeaderString());
        if (authorizationHeader != null && authorizationHeader.startsWith(jwtProperties.getTokenPrefix())) {
            String token = authorizationHeader.replace(jwtProperties.getTokenPrefix() + " ", "");
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(token));
        }
        chain.doFilter(req, res);
    }

    private Authentication getAuthentication(String token) {

        // Remove the prefix from the authorization header value to return only the JWT.
        Claims claims = Jwts.parser().setSigningKey(jwtProperties.getSecret().getBytes()).parseClaimsJws(token).getBody();
        // Check if the token is blacklisted, if so, throw an exception
        UserService.UserDetailsImpl userDetails = new UserService.UserDetailsImpl(userService.findById(Integer.parseInt(claims.getSubject())));
        return new UsernamePasswordAuthenticationToken(userDetails, token, userDetails.getAuthorities());

    }


}
