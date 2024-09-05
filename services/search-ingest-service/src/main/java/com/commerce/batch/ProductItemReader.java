package com.commerce.batch;

import com.commerce.product.ProductClient;
import com.commerce.product.ProductRecord;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class ProductItemReader implements ItemReader<List<ProductRecord>> {
    private ProductClient productClient;

    public ProductItemReader() {
    }

    @Override
    public List<ProductRecord> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return fetchProductDataFromAPI();
    }

    private List<ProductRecord> fetchProductDataFromAPI() {

        ResponseEntity<List<ProductRecord>> response = productClient.findAll();

        return response.getBody();
    }
}
