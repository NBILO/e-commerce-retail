package com.commerce.service;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class IndexService {

    final private co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient;

    public IndexService(co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    private void createIndex(String indexName) throws IOException {
        this.elasticsearchClient.indices().create(c -> c
                .index(indexName)
        );
    }

    private static void createDocuments(ElasticsearchClient httpClient) throws IOException {
        Employee employee = new Employee();
        employee.setEmplId(201);
        employee.setName("Alan Donald");
        employee.setDept("Sports");

        IndexResponse response = httpClient.index(i -> i
                .index("employee_idx")
                .id(employee.getEmplId().toString())
                .document(employee)
        );

        System.out.println("\n" + "Indexed with version " + response.version());
    }

}