package com.commerce.batch;

import com.commerce.product.ProductRecord;
import com.commerce.service.ProductIndexService;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class ProductItemWriter implements ItemWriter<List<ProductRecord>> {

    private final ProductIndexService productIndexService;
    private final String indexName;

    public ProductItemWriter(String indexName, ProductIndexService productIndexService) {
        this.productIndexService = productIndexService;
        this.indexName = indexName;
    }

    @Override
    public void write(Chunk<? extends List<ProductRecord>> chunk) throws Exception {
        for (List<ProductRecord> list : chunk.getItems()) {
            productIndexService.ingestBulkRequest(indexName, list);
        }
    }

}
