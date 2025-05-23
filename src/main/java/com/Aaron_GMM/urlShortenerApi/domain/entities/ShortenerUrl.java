package com.Aaron_GMM.urlShortenerApi.domain.entities;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;
@Document(collection = "shortened_Url")
public class ShortenerUrl {
    @Id
    private  String Id;

    @Indexed(unique = true)
    private  String shortCode;
    private  String originalUrl;
    private  String apiClienteID;

    public ShortenerUrl(String id, String apiClienteID, String originalUrl, String shortCode) {
        this.Id = id;
        this.apiClienteID = apiClienteID;
        this.originalUrl = originalUrl;
        this.shortCode = shortCode;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        ShortenerUrl that = (ShortenerUrl) o;
        return Objects.equals(Id, that.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }
}
