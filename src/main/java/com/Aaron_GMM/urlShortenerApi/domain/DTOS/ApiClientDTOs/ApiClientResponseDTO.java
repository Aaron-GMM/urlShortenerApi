package com.Aaron_GMM.urlShortenerApi.domain.DTOS.ApiClientDTOs;

public class ApiClientResponseDTO {
    private String Id;
    private String apiSecret;
    private String name;
    private String status;

    public ApiClientResponseDTO(String id, String apiSecret, String name, String status) {
        this.Id = id;
        this.apiSecret = apiSecret;
        this.name = name;
        this.status = status;
    }
    public ApiClientResponseDTO(){}

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
