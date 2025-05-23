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

        // Supondo que ApiClient tenha um campo 'clientId' que é único e indexado
        ApiClient apiClient = apiClientRepository.findByClientId(clientId).orElse(null);

        if (apiClient != null &&
                apiClient.getStatus() == ApiClientStatus.ACTIVE &&
                // (Opcional) Verifique a data de validade se você implementou 'validUntil'
                // (apiClient.getValidUntil() == null || LocalDateTime.now().isBefore(apiClient.getValidUntil())) &&
                passwordEncoder.matches(apiSecret, apiClient.getApiKeyHash())) {

            // Autenticado com sucesso!
            // O "principal" pode ser o ID do cliente ou o objeto ApiClient
            // As "authorities" podem vir dos roles do ApiClient, se houver
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    apiClient.getId(), // ou apiClient objeto
                    null, // Credenciais não são mais necessárias aqui
                    Collections.emptyList() // ou authorities derivadas dos roles do apiClient
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else {
            // Falha na autenticação - você pode limpar o contexto ou tratar o erro aqui,
            // mas geralmente se nenhum 'Authentication' é setado, o acesso será negado
            // mais tarde por regras de autorização ou por um 'AuthenticationEntryPoint'.
            SecurityContextHolder.clearContext();
            // response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "API Key inválida ou não autorizada");
            // return; // Se quiser interromper a cadeia aqui em caso de falha
        }

        filterChain.doFilter(request, response);
    }
}

