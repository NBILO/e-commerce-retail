package com.commerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutoCompleteService {
    final private co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient;

    public AutoCompleteService(co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }
}
