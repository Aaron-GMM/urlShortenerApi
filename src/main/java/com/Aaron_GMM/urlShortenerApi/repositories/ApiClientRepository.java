package com.Aaron_GMM.urlShortenerApi.repositories;

import com.Aaron_GMM.urlShortenerApi.domain.entities.ApiClient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ApiClientRepository extends MongoRepository<ApiClient,String> {
   // Optional<ApiClient> findById(String clientId);
}
