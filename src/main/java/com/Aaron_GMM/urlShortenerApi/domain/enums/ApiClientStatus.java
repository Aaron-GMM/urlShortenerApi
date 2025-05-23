package com.Aaron_GMM.urlShortenerApi.domain.enums;

public enum ApiClientStatus {
    ACTIVE("ativo"),
    INACTIVE("inativo");

    private String status;
    ApiClientStatus(String status){this.status = status;}

    public String getStatus() {
        return status;
    }
}
