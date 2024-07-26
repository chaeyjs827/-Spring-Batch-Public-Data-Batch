package com.perfect.public_data.domain.general.restaurant;

import com.perfect.public_data.domain.general.restaurant.listener.GeneralRestaurantJobListener;
import com.perfect.public_data.domain.general.restaurant.step.GeneralRestaurantReadAndSaveStep;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing(
        tablePrefix = "PUBLIC_DATA_BATCH_"
)
public class GeneralRestaurantImportConfig {

    public static final String JOB_NAME = "generalRestaurantImportJob";

    private final JobRepository jobRepository;
    private final GeneralRestaurantReadAndSaveStep generalRestaurantReadAndSaveStep;
    private final GeneralRestaurantJobListener generalRestaurantJobListener;

    public GeneralRestaurantImportConfig(
            JobRepository jobRepository
            , GeneralRestaurantReadAndSaveStep generalRestaurantReadAndSaveStep
            , GeneralRestaurantJobListener generalRestaurantJobListener
    ) {
        this.jobRepository = jobRepository;
        this.generalRestaurantReadAndSaveStep = generalRestaurantReadAndSaveStep;
        this.generalRestaurantJobListener = generalRestaurantJobListener;
    }

    @Bean(JOB_NAME)
    public Job job() throws Exception {
        System.out.println("[JOB] " + JOB_NAME);
        return new JobBuilder(JOB_NAME, jobRepository)
                .start(generalRestaurantReadAndSaveStep.step())
                .listener(generalRestaurantJobListener)
                .build();
    }

}
