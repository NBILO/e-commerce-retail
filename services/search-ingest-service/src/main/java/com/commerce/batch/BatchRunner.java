package com.commerce.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class BatchRunner implements CommandLineRunner {
    private final ApplicationContext applicationContext;
    private final JobLauncher jobLauncher;

    public BatchRunner(ApplicationContext applicationContext, JobLauncher jobLauncher) {
        this.applicationContext = applicationContext;
        this.jobLauncher = jobLauncher;
    }

    @Override
    public void run(String... args) throws Exception {

        Job job = (Job) this.applicationContext.getBean("IngestProductJob");

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("JobID", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();

        var jobExecution = jobLauncher.run(job, jobParameters);

        var batchStatus = jobExecution.getStatus();
        while (batchStatus.isRunning()) {
            System.out.println("Still running...");
            Thread.sleep(5000L);
        }
    }
}