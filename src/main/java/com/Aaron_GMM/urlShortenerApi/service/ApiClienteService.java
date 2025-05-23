package com.Aaron_GMM.urlShortenerApi.service;


import com.Aaron_GMM.urlShortenerApi.domain.entities.ApiClient;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientRole;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientStatus;
import com.Aaron_GMM.urlShortenerApi.repositories.ApiClientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class ApiClienteService {

    private final ApiClientRepository apiClientRepository;
    private final PasswordEncoder passwordEncoder;

    public ApiClienteService(ApiClientRepository apiClientRepository, PasswordEncoder passwordEncoder) {
        this.apiClientRepository = apiClientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Retorna o ClientID e a Chave Secreta (mostrar apenas uma vez!)
    public Pair<String, String> createApiClient(String clientName, ApiClientRole roles) {
        String clientId = generateUniqueClientId(); // Gere um ID público
        String apiSecret = generateRawApiSecret();   // Gere o segredo
        String apiKeyHash = passwordEncoder.encode(apiSecret); // Hasheie o segredo

        ApiClient newClient = new ApiClient();
        // Supondo que você adicionou o campo clientId e ajustou o construtor:
        // newClient.setId(UUID.randomUUID().toString()); // ID interno do MongoDB
        newClient.setId(clientId);
        newClient.setName(clientName); // Usando o nome do campo da sua entidade
        newClient.setApiKeyHash(apiKeyHash);
        newClient.setStatus(ApiClientStatus.ACTIVE);
        newClient.setRoles(ApiClientRole.ADMIN);

        apiClientRepository.save(newClient);

        // NUNCA retorne o apiKeyHash. Retorne o clientId e o apiSecret original.
        return Pair.of(clientId, apiSecret);
    }

    private String generateUniqueClientId() {
        // Exemplo: "client_" + UUID.randomUUID().toString().substring(0, 8);
        return "client_" + UUID.randomUUID().toString().replace("-", "").substring(0, 16);
    }

    private String generateRawApiSecret() {
        // Exemplo: "secret_" + UUID.randomUUID().toString() + UUID.randomUUID().toString();
        return "secret_" + UUID.randomUUID().toString().replace("-", "") +
                UUID.randomUUID().toString().replace("-", "");
    }

    // Classe auxiliar simples para retornar o par de chaves
    public static class Pair<K, V> {
        public final K key;
        public final V value;
        private Pair(K key, V value) { this.key = key; this.value = value; }
        public static <K, V> Pair<K, V> of(K key, V value) { return new Pair<>(key, value); }
    }
}

