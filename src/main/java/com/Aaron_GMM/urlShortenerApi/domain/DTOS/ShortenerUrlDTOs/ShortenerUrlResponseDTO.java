package com.Aaron_GMM.urlShortenerApi.domain.DTOS.ShortenerUrlDTOs;

public class ShortenerUrlResponseDTO {

    @Id
    private  String Id;

    private  String shortCode;
    private  String originalUrl;
    private  String apiClienteID;

    public ShortenerUrlResponseDTO(String id, String apiClienteID, String originalUrl, String shortCode) {
        this.Id = id;
        this.apiClienteID = apiClienteID;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
    }
    public ShortenerUrlResponseDTO(){}

    public String getId() {
        return Id;
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
        this.Id = id;
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


}
