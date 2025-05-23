package com.Aaron_GMM.urlShortenerApi.domain.entities;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientStatus;

import java.util.Objects;
import java.util.Set;

public class ApiClient {
    private String Id;
    private String Name;
    private String apiKeyHash;
    private ApiClientStatus status;
    private Set<String> roles;

    public ApiClient(String id, String name, String apiKeyHash, ApiClientStatus status, Set<String> roles) {
        Id = id;
        Name = name;
        this.apiKeyHash = apiKeyHash;
        this.status = status;
        this.roles = roles;
    }

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

    public Set<String> getRoles() {
        return roles;
    }

    public void setRoles(Set<String> roles) {
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
