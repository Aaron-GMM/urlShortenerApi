package com.Aaron_GMM.urlShortenerApi.web.controller;
import com.Aaron_GMM.urlShortenerApi.domain.DTOS.ApiClientDTOs.ApiClientDetailsResponseDTO;
import com.Aaron_GMM.urlShortenerApi.domain.DTOS.ApiClientDTOs.ApiClientRequestDTO;
import com.Aaron_GMM.urlShortenerApi.domain.DTOS.ApiClientDTOs.ApiClientResponseDTO;
import com.Aaron_GMM.urlShortenerApi.domain.entities.ApiClient;
import com.Aaron_GMM.urlShortenerApi.service.ApiClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/clients")
public class ApiClientController {
    @Autowired
    private ApiClienteService apiClienteService;

    @PostMapping("/register")
    public ResponseEntity<ApiClientResponseDTO> createApiClient(@Valid @RequestBody ApiClientRequestDTO clientRequestDTO){
        ApiClienteService.Pair<String,String> clientKey = apiClienteService.createApiClient(clientRequestDTO.getName(),clientRequestDTO.getRole());
        ApiClientResponseDTO responseDTO = new ApiClientResponseDTO(
                clientKey.key,
                clientKey.value,
                clientRequestDTO.getName(),
                "ACTIVE"
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}

