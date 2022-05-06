package com.piml.products.service;

import com.piml.products.dto.SellerDTO;
import com.piml.products.exception.SellerAlreadyExistsException;
import com.piml.products.exception.handler.RestTemplateResponseErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class SellerAPIService {
    private static final String PRODUCT_API_URI = "http://gandalf:8080";
    private static final String PRODUCTS_RESOURCE = "/user/v1";

    private final RestTemplate restTemplate;

    @Autowired
    public SellerAPIService(RestTemplateBuilder restTemplateBuilder) {
        restTemplate = restTemplateBuilder
                .errorHandler(new RestTemplateResponseErrorHandler())
                .build();
    }

    public SellerAPIService(@Lazy RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SellerDTO create(SellerDTO sellerDTO) {
        String resourceURI = PRODUCT_API_URI.concat(PRODUCTS_RESOURCE);
        ResponseEntity<SellerDTO> result = restTemplate.postForEntity(resourceURI, sellerDTO, SellerDTO.class);
        if(result.getStatusCode().is4xxClientError()) {
            throw new SellerAlreadyExistsException("Seller already exists");
        }else if (!result.getStatusCode().is2xxSuccessful())
            throw new RuntimeException("Chamada para API não sucedeu.");
        return result.getBody();
    }

    public SellerDTO getById(Long id) {
        String resourceURI = PRODUCT_API_URI.concat(PRODUCTS_RESOURCE).concat("/").concat(String.valueOf(id));
        ResponseEntity<SellerDTO> result = restTemplate.getForEntity(resourceURI, SellerDTO.class);
        if (!result.getStatusCode().is2xxSuccessful())
            throw new RuntimeException("Chamada para API não sucedeu.");
        return result.getBody();
    }
}