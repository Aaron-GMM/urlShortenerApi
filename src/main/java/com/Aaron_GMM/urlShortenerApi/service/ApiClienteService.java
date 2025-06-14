package com.Aaron_GMM.urlShortenerApi.service;


import com.Aaron_GMM.urlShortenerApi.domain.DTOS.ApiClientDTOs.ApiClientRequestDTO;
import com.Aaron_GMM.urlShortenerApi.domain.entities.ApiClient;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientRole;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientStatus;
import com.Aaron_GMM.urlShortenerApi.repositories.ApiClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ApiClienteService {
    @Autowired
    private  ApiClientRepository apiClientRepository;
    @Autowired
    private  PasswordEncoder passwordEncoder;


    public Pair<String, String> createApiClient(String clientName, ApiClientRole roles) {
        String clientId = generateUniqueClientId();
        String apiSecret = generateRawApiSecret();
        String apiKeyHash = passwordEncoder.encode(apiSecret);

        ApiClient newClient = new ApiClient();
        newClient.setId(clientId);
        newClient.setName(clientName);
        newClient.setApiKeyHash(apiKeyHash);
        newClient.setStatus(ApiClientStatus.ACTIVE);
        newClient.setRoles(roles);

        apiClientRepository.save(newClient);

        return Pair.of(clientId, apiSecret);
    }

    public Optional<ApiClient> findApiClientById(String id) {
        return apiClientRepository.findById(id);
    }

    public List<ApiClient> findAllApiClients() {
        return apiClientRepository.findAll();
    }
    private String generateUniqueClientId() {
        return "client_" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    private String generateRawApiSecret() {
        return "secret_" + UUID.randomUUID().toString().replace("-", "") +
                UUID.randomUUID().toString().replace("-", "");
    }

    public static class Pair<K, V> {
        public final K key;
        public final V value;
        private Pair(K key, V value) { this.key = key; this.value = value; }
        public static <K, V> Pair<K, V> of(K key, V value) { return new Pair<>(key, value); }
    }
}

