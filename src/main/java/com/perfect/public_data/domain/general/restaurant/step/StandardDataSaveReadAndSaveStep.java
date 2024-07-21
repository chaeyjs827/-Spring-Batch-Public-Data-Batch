package com.perfect.public_data.domain.general.restaurant.step;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcPagingItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@Configuration
public class StandardDataSaveReadAndSaveStep implements StepExecutionListener {

    private static final String STEP_NAME = "standardDataSaveReadAndSaveStep";

    private static final Integer CHUNK_SIZE = 5000;

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;

    public StandardDataSaveReadAndSaveStep(
            JobRepository jobRepository
            , PlatformTransactionManager platformTransactionManager
            , DataSource datasource
    ) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
    }

    @Bean(STEP_NAME + "_stepManager")
    @JobScope
    public Step step() throws Exception {
        System.out.println("[STEP] " + STEP_NAME);
        return new StepBuilder(STEP_NAME, jobRepository)
                .<GeneralRestaurantRow, GeneralRestaurantRow> chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(reader())
                .writer(writer())
                .build();
    }

    @Bean(STEP_NAME + "reader")
    @StepScope
    public FlatFileItemReader<GeneralRestaurantRow> reader() {
        return new FlatFileItemReaderBuilder<GeneralRestaurantRow>()
                .name("personItemReader")
                .resource(new ClassPathResource("test.csv"))
                .delimited()
                .names(new String[]{"name", "age", "email"})
                .fieldSetMapper(new BeanWrapperFieldSetMapper<GeneralRestaurantRow>() {{
                    setTargetType(GeneralRestaurantRow.class);
                }})
                .build();
    }

    @Bean(STEP_NAME + "writer")
    @StepScope
    public ItemWriter<GeneralRestaurantRow> writer() {
        return chunk -> {};
    }

}
