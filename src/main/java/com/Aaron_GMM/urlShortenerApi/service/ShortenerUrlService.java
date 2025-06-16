package com.Aaron_GMM.urlShortenerApi.service;

import com.Aaron_GMM.urlShortenerApi.domain.entities.ShortenerUrl;
import com.Aaron_GMM.urlShortenerApi.repositories.ShortenerUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
public class ShortenerUrlService {
    @Autowired
    private ShortenerUrlRepository urlRepository;



    private static final String CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 7;
    private static final SecureRandom RANDOM = new SecureRandom();

    public ShortenerUrl createShortUrl(String originalUrl, String apiClientId) {
        String shortCode = generateUniqueShortCode();

        ShortenerUrl newUrl = new ShortenerUrl(null, apiClientId, originalUrl, shortCode);
        return urlRepository.save(newUrl);
    }

    public Optional<ShortenerUrl> resolveShortUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode);
    }

    private String generateUniqueShortCode() {
        String code;
        do {
            StringBuilder sb = new StringBuilder(CODE_LENGTH);
            for (int i = 0; i < CODE_LENGTH; i++) {
                sb.append(CHARS.charAt(RANDOM.nextInt(CHARS.length())));
            }
            code = sb.toString();
        } while (urlRepository.findByShortCode(code).isPresent());
        return code;
    }
}
