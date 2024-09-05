package com.commerce.service;

import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public  abstract class IndexServiceGeneric<T> {

    final private co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient;

    public IndexServiceGeneric(co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public boolean createIndex(String indexName, String settingPath) throws IOException {
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

    public boolean ingestBulkRequest(String indexName, List<T> documents) throws IOException {

        BulkRequest.Builder br = new BulkRequest.Builder();
        for (T document : documents) {
            br.operations(op -> op
                    .index(idx -> idx
                            .index(indexName)
                            .document(document)
                    )
            );
        }
        BulkResponse result = elasticsearchClient.bulk(br.build());
        return !result.errors();
    }
}