package com.Aaron_GMM.urlShortenerApi.infra.security.config;

import com.Aaron_GMM.urlShortenerApi.infra.security.ApiKeyAuthFilter;
import com.Aaron_GMM.urlShortenerApi.repositories.ApiClientRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {
    @Autowired
    private ApiClientRepository apiClientRepository;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public ApiKeyAuthFilter apiKeyAuthFilter() {
        return new ApiKeyAuthFilter(apiClientRepository, passwordEncoder());
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(
                csrf->csrf.disable()
        ).sessionManagement(
                session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ).authorizeHttpRequests(authz->authz.requestMatchers("/api/").authenticated()
                .anyRequest().permitAll()
                ).addFilterBefore(apiKeyAuthFilter(), UsernamePasswordAuthenticationFilter.class)
                // Trata falhas de autenticação retornando 401 Unauthorized
                .exceptionHandling(exceptions ->
                        exceptions.authenticationEntryPoint((request, response, authException) ->
                                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso não autorizado: " + authException.getMessage())
                        )
                );
    return http.build();
    }

}
