package com.commerce.config;
import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.cluster.HealthResponse;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.ElasticsearchTransport;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;


import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Configuration
public class ElasticApiClient {

    public  ElasticsearchClient getElasticsearchclient() throws IOException {
        BasicCredentialsProvider credsProv = new BasicCredentialsProvider();
        credsProv.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("elastic", "NlfGhKDDy=36FpvM*o0l"));

        RestClient restClient = RestClient
                .builder(new HttpHost("localhost", 9200, "http"))
                .setHttpClientConfigCallback(hc -> hc
                        .setDefaultCredentialsProvider(credsProv)
                )
                .build();

        ElasticsearchTransport transport = new RestClientTransport(restClient, new JacksonJsonpMapper());
        ElasticsearchClient client = new ElasticsearchClient(transport);

        //to check Elastic server health and connection status
        HealthResponse healthResponse = client.cluster().health();
        System.out.printf("Elasticsearch status is: [%s]", healthResponse.status());
        return client;
    }

}
