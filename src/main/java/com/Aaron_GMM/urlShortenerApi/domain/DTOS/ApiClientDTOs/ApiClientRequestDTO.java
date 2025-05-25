package com.Aaron_GMM.urlShortenerApi.domain.DTOS.ApiClientDTOs;
import jakarta.validation.constraints.NotBlank;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientRole;
import com.Aaron_GMM.urlShortenerApi.domain.enums.ApiClientStatus;
import jakarta.validation.constraints.Size;

public class ApiClientRequestDTO {
    @NotBlank(message = "O nome do cliente nao pode ser vazio")
    @Size(min = 3,max = 150,message = "O nome tem que ter entre 3 a 150 caracteres")
    private String Name;
    private ApiClientRole role;
    private ApiClientStatus status;
    private String apiKeyHash;
    public ApiClientRequestDTO(String name, ApiClientRole role, ApiClientStatus status, String apiKeyHash){
        this.Name= name;
        this.role = role;
        this.status = status;
        this.apiKeyHash = apiKeyHash;
    }
    public ApiClientRequestDTO(){}
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public ApiClientRole getRole() {
        return role;
    }

    public void setRole(ApiClientRole role) {
        this.role = role;
    }

    public ApiClientStatus getStatus() {
        return status;
    }

    public void setStatus(ApiClientStatus status) {
        this.status = status;
    }

    public String getApiKeyHash() {
        return apiKeyHash;
    }

    public void setApiKeyHash(String apiKeyHash) {
        this.apiKeyHash = apiKeyHash;
    }

}
