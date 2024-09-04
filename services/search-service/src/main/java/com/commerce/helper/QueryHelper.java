package com.commerce.helper;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.TotalHits;
import co.elastic.clients.elasticsearch.core.search.TotalHitsRelation;
import com.commerce.dto.ProductEs;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
@Slf4j
public class QueryHelper {
    public static List<ProductEs> getProductEs(SearchResponse<ProductEs> response, List<ProductEs> listProduct) {
        TotalHits total = response.hits().total();
        assert total != null;
        boolean isExactResult = total.relation() == TotalHitsRelation.Eq;

        if (isExactResult) {
            log.info("There are " + total.value() + " results");
        } else {
            log.info("There are more than " + total.value() + " results");
        }

        List<Hit<ProductEs>> hits = response.hits().hits();
        for (Hit<ProductEs> hit : hits) {
            ProductEs product = hit.source();
            listProduct.add(product);
            log.info("Found product " + product + ", score " + hit.score());
        }
        return listProduct;
    }
}
