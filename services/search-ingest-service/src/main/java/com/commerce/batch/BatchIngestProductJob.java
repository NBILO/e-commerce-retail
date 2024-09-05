package com.commerce.batch;

import com.commerce.product.ProductRecord;
import com.commerce.service.ProductIndexService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
public class BatchIngestProductJob {
    private final JobRepository jobRepository;
    private final ProductIndexService productIndexService;

    public BatchIngestProductJob(JobRepository jobRepository, ProductIndexService productIndexService) {
        this.jobRepository = jobRepository;
        this.productIndexService = productIndexService;
    }

    @Bean
    public Job ingestProductsJob(Step step1) {
        var name = "IngestProductJob";
        var builder = new JobBuilder(name, jobRepository);
        return builder.start(step1).build();
    }

    @Bean
    public Step indexBulkDocumentStep(ItemReader<List<ProductRecord>> itemReader,
                                      ItemWriter<List<ProductRecord>> itemWriter, PlatformTransactionManager txManager) {
        var name = "ingest products into Elasticsearch";
        var builder = new StepBuilder(name, jobRepository);
        return builder
                .<List<ProductRecord>, List<ProductRecord>>chunk(5, txManager)
                .reader(itemReader)
                .writer(itemWriter)
                .build();
    }


    @Bean
    public ItemReader<List<ProductRecord>> itemReader() {
        return new ProductItemReader();

    }

    @Bean
    public ItemWriter<List<ProductRecord>> itemWriter() {
        return new ProductItemWriter("product_search", productIndexService);

    }

}
