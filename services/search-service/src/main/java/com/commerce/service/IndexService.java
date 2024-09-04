package com.commerce.service;

import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import com.commerce.dto.ProductEs;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class IndexService {

    final private co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient;

    public IndexService(co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    private boolean createIndex(String indexName, String settingPath) throws IOException {
        CreateIndexRequest req;
        try (InputStream settings = this.getClass()
                .getResourceAsStream(settingPath)) {
            req = CreateIndexRequest.of(b -> b
                    .index(indexName)
                    .withJson(settings));
        }
        boolean created = elasticsearchClient.indices().create(req).acknowledged();
        this.elasticsearchClient.indices().create(c -> c
                .index(indexName)
        );
        return created;
    }

    private boolean ingestBulkRequest(String indexName) throws IOException {
        List<ProductEs> products = fetchProducts();
        BulkRequest.Builder br = new BulkRequest.Builder();

        for (ProductEs product : products) {
            br.operations(op -> op
                    .index(idx -> idx
                            .index(indexName)
                            .id(product.getId())
                            .document(product)
                    )
            );
        }
        BulkResponse result = elasticsearchClient.bulk(br.build());
        return !result.errors();
    }


    private List<ProductEs> fetchProducts() {
        return new ArrayList<>();
    }
}