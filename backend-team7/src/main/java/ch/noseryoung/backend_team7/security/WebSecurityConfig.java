package ch.noseryoung.backend_team7.security;

import ch.noseryoung.backend_team7.domain.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JWTProperties jwtProperties;

    @Bean
    public AuthenticationManager authenticationManager() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userService);
        return new ProviderManager(provider);
    }

    //DOING
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(
                requests -> requests.requestMatchers(HttpMethod.GET, "/v3/api-docs", "/v3/api-docs/swagger-config", "/swagger-ui/*").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login") // JWT-Input: Expose login endpoint
                        .permitAll()
                        .anyRequest().authenticated())
                // JWT-Input: Adds JWT Authentication Filter, must have same endpoint as exposed endpoint for login
                .addFilterAfter(new JWTAuthenticationFilter(authenticationManager(), new AntPathRequestMatcher("/login", "POST"), jwtProperties), UsernamePasswordAuthenticationFilter.class)
                .addFilterAfter(new JWTAuthorizationFilter(userService, jwtProperties),
                        UsernamePasswordAuthenticationFilter.class)


                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .build();
    }
}
