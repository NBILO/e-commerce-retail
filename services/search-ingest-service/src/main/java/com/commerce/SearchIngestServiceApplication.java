package com.commerce;

import com.commerce.batch.BatchRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SearchIngestServiceApplication {
    @Autowired
    private static BatchRunner batchRunner;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(SearchIngestServiceApplication.class, args);
        batchRunner.run();
    }

}
