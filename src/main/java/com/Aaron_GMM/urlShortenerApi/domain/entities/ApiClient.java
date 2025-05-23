package com.Aaron_GMM.urlShortenerApi.domain.entities;
import java.util.Objects;

public class ApiClient {
    private  String id;
    private  String shortCode;
    private  String originalUrl;
    private  String apiClienteID;

    public ApiClient(String id, String apiClienteID, String originalUrl, String shortCode) {
        this.id = id;
        this.apiClienteID = apiClienteID;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
    }

    public String getId() {
        return id;
    }

    public String getShortCode() {
        return shortCode;
    }

    public String getApiClienteID() {
        return apiClienteID;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setShortCode(String shortCode) {
        this.shortCode = shortCode;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public void setApiClienteID(String apiClienteID) {
        this.apiClienteID = apiClienteID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ApiClient apiClient = (ApiClient) o;
        return Objects.equals(id, apiClient.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
