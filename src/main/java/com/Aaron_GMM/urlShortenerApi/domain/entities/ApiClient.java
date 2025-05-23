package com.Aaron_GMM.urlShortenerApi.domain.entities;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientRole;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientStatus;
import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
@Document(collection = "ApiClients")
public class ApiClient {
    @Id
    private String Id;
    private String Name;
    @Indexed(unique = true)
    private String apiKeyHash;
    private ApiClientStatus status;
    private ApiClientRole roles;

    public ApiClient(String id, String name, String apiKeyHash, ApiClientStatus status, ApiClientRole roles) {
        Id = id;
        Name = name;
        this.apiKeyHash = apiKeyHash;
        this.status = status;
        this.roles = roles;
    }
    public ApiClient(){}

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getApiKeyHash() {
        return apiKeyHash;
    }

    public void setApiKeyHash(String apiKeyHash) {
        this.apiKeyHash = apiKeyHash;
    }

    public ApiClientStatus getStatus() {
        return status;
    }

    public void setStatus(ApiClientStatus status) {
        this.status = status;
    }

    public ApiClientRole getRoles() {
        return roles;
    }

    public void setRoles(ApiClientRole roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ApiClient apiClient = (ApiClient) o;
        return Objects.equals(Id, apiClient.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }
}
