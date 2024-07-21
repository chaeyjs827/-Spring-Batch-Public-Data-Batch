package com.perfect.public_data.domain.general.restaurant;

import com.perfect.public_data.domain.general.restaurant.step.StandardDataSaveReadAndSaveStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing(
        tablePrefix = "public_data_batch_"
)
public class GeneralRestaurantImportConfig {

    public static final String JOB_NAME = "generalRestaurantImportJob";

    private final JobRepository jobRepository;
    private final StandardDataSaveReadAndSaveStep standardDataSaveReadAndSaveStep;

    public GeneralRestaurantImportConfig(
            JobRepository jobRepository
            , StandardDataSaveReadAndSaveStep standardDataSaveReadAndSaveStep
    ) {
        this.jobRepository = jobRepository;
        this.standardDataSaveReadAndSaveStep = standardDataSaveReadAndSaveStep;
    }

    @Bean(JOB_NAME)
    public Job job() throws Exception {
        System.out.println("[JOB] " + JOB_NAME);
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(standardDataSaveReadAndSaveStep.step())
                .build();
    }

}
