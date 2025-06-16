package com.Aaron_GMM.urlShortenerApi.web.controller;


import com.Aaron_GMM.urlShortenerApi.domain.DTOS.ShortenerUrlDTOs.ShortenerUrlRequestDTO;
import com.Aaron_GMM.urlShortenerApi.domain.DTOS.ShortenerUrlDTOs.ShortenerUrlResponseDTO;
import com.Aaron_GMM.urlShortenerApi.domain.entities.ShortenerUrl;
import com.Aaron_GMM.urlShortenerApi.service.ShortenerUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/url/")
public class ShortenerUrlController {
        @Autowired
        private ShortenerUrlService urlService;

        @PostMapping("/shorten")
        public ResponseEntity<ShortenerUrlResponseDTO> shortenUrl(@RequestBody ShortenerUrlRequestDTO shortenerUrlRequestDTO, Authentication authentication){

            String apiClientId = (String) authentication.getPrincipal();
            ShortenerUrl shortenerUrl =  urlService.createShortUrl(shortenerUrlRequestDTO.getOriginalUrl(), apiClientId);


            String fullShortUrl = "http://localhost:8080/" + shortenerUrl.getShortCode();
            ShortenerUrlResponseDTO  responseDTO = new ShortenerUrlResponseDTO(
                    shortenerUrl.getId(),
                    shortenerUrl.getApiClienteID(),
                    shortenerUrl.getOriginalUrl(),
                    fullShortUrl
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);

        }

}
