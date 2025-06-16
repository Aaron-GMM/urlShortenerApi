package com.Aaron_GMM.urlShortenerApi.infra.security;

import com.Aaron_GMM.urlShortenerApi.domain.entities.ApiClient;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientStatus;
import com.Aaron_GMM.urlShortenerApi.repositories.ApiClientRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

public class ApiKeyAuthFilter extends OncePerRequestFilter {
    private final ApiClientRepository apiClientRepository;
    private final PasswordEncoder passwordEncoder;
    public ApiKeyAuthFilter(ApiClientRepository apiClientRepository, PasswordEncoder passwordEncoder) {
        this.apiClientRepository = apiClientRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String clientId = request.getHeader("X-Client-ID");
        String apiSecret = request.getHeader("X-API-Secret");

        if (clientId == null || apiSecret == null) {
            filterChain.doFilter(request, response); // Deixa outros filtros lidarem ou falha
            return;
        }
        ApiClient apiClient = apiClientRepository.findById(clientId).orElse(null);

        if (apiClient != null &&
                apiClient.getStatus() == ApiClientStatus.ACTIVE &&

                passwordEncoder.matches(apiSecret, apiClient.getApiKeyHash())) {


            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    apiClient.getId(), // ou apiClient objeto
                    null, // Credenciais não são mais necessárias aqui
                    Collections.emptyList() // ou authorities derivadas dos roles do apiClient
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            filterChain.doFilter(request, response);

        } else {
            SecurityContextHolder.clearContext();
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("API Key or Secret is invalid.");
            return; // IMPORTANTE: Interrompe a execução aqui.
        }

    }
}

