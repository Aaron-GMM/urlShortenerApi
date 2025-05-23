package com.Aaron_GMM.urlShortenerApi.domain.DTOS.ApiClientDTOs;

import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientRole;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientStatus;

public class ApiClientDetailsResponseDTO {
    private String id;
    private String clientId;
    private String name;
    private ApiClientStatus status;
    private ApiClientRole roles;

    public ApiClientDetailsResponseDTO(String id, String clientId, String name, ApiClientStatus status, ApiClientRole roles) {
        this.id = id;
        this.clientId = clientId;
        this.name = name;
        this.status = status;
        this.roles = roles;
    }
    public ApiClientDetailsResponseDTO(){}
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public ApiClientStatus getStatus() { return status; }
    public void setStatus(ApiClientStatus status) { this.status = status; }
    public ApiClientRole getRoles() { return roles; }
    public void setRoles(ApiClientRole roles) { this.roles = roles; }
}

