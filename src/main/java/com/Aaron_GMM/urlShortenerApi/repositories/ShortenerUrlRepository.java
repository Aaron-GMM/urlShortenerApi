package com.Aaron_GMM.urlShortenerApi.repositories;

import com.Aaron_GMM.urlShortenerApi.domain.entities.ShortenerUrl;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShortenerUrlRepository extends MongoRepository<ShortenerUrl, String> {
    Optional<ShortenerUrl> findByShortCode(String shortCode);
}
