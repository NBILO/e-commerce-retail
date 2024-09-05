package com.commerce;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.commerce.dto.ProductEs;
import com.commerce.helper.QueryHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class SearchService {

    final private co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient;

    public SearchService(co.elastic.clients.elasticsearch.ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;

    }

    public List<ProductEs> search(String query) throws IOException {

        List<ProductEs> listProduct = new ArrayList<>();
        SearchResponse<ProductEs> response = elasticsearchClient.search(s -> s
                        .index("product_search")
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
