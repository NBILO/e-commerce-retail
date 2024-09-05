package com.commerce.service;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.commerce.product.ProductRecord;
import org.springframework.stereotype.Service;

@Service
public class ProductIndexService extends IndexServiceGeneric<ProductRecord>{
    public ProductIndexService(ElasticsearchClient elasticsearchClient) {
        super(elasticsearchClient);
    }
}
