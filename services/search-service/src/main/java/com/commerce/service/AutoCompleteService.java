package com.commerce.service;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.commerce.dto.ProductEs;
import com.commerce.helper.QueryHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AutoCompleteService {
    final private co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient;

    public AutoCompleteService(co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }
    public List<ProductEs> performAutocompleteSearch(String query) throws IOException {

        List<ProductEs> listProduct = new ArrayList<>();
        SearchResponse<ProductEs> response = elasticsearchClient.search(s -> s
                        .index("product_autocomplete")
                        .query(q -> q
                                .match(t -> t
                                        .field("name")
                                        .query(query)
                                )
                        ),
                ProductEs.class
        );

        return QueryHelper.getProductEs(response, listProduct);
    }
}
