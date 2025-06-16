package com.Aaron_GMM.urlShortenerApi.domain.DTOS.ShortenerUrlDTOs;

import org.springframework.data.mongodb.core.index.Indexed;

public class ShortenerUrlRequestDTO {

    private  String shortCode;
    private  String originalUrl;
    private  String apiClienteID;

    public ShortenerUrlRequestDTO( String apiClienteID, String originalUrl, String shortCode) {
        this.apiClienteID = apiClienteID;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
    }
    public ShortenerUrlRequestDTO(){}



    public String getShortCode() {
        return shortCode;
    }

    public String getApiClienteID() {
        return apiClienteID;
    }

    public String getOriginalUrl() {
        return originalUrl;
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
