package com.Aaron_GMM.urlShortenerApi.domain.enums;

public enum ApiClientRole {
    ADMIN("admin"),
    User("user");
    private String role;

    ApiClientRole(String role){this.role = role;}
    public String getRole(){return role;}
}
