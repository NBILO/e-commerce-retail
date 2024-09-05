package com.commerce.product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(
        name = "product-service",
        url = "${application.config.product-url}"
)
public interface ProductClient {

    @GetMapping
    ResponseEntity<List<ProductRecord>> findAll();
}
