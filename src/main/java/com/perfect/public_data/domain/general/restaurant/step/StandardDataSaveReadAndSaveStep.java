package com.perfect.public_data.domain.general.restaurant.step;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import com.perfect.public_data.domain.general.restaurant.mapper.GeneralRestaurantLineMapper;
import com.perfect.public_data.domain.general.restaurant.repository.GerneralRestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@Slf4j
public class StandardDataSaveReadAndSaveStep implements StepExecutionListener {

    private static final String STEP_NAME = "standardDataSaveReadAndSaveStep";

    private static final Integer CHUNK_SIZE = 5000;

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final GerneralRestaurantRepository gerneralRestaurantRepository;

    public StandardDataSaveReadAndSaveStep(
            JobRepository jobRepository
            , PlatformTransactionManager platformTransactionManager
            , DataSource datasource
            , GerneralRestaurantRepository gerneralRestaurantRepository
    ) {
        this.jobRepository = jobRepository;
        this.platformTransactionManager = platformTransactionManager;
        this.gerneralRestaurantRepository = gerneralRestaurantRepository;
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
                .resource(new ClassPathResource("general-restaurant/test.csv"))
                .delimited()
                .names(new String[]{
                        "id", "open_service_name", "open_service_id", "open_local_goverment_code",
                        "manage_id"
                })
                .lineMapper(new GeneralRestaurantLineMapper())
                .linesToSkip(1)
                .build();
    }

    @Bean(STEP_NAME + "writer")
    @StepScope
    public ItemWriter<GeneralRestaurantRow> writer() {
        return chunk -> {
            List<GeneralRestaurantRow> list = (List<GeneralRestaurantRow>) chunk.getItems();
            gerneralRestaurantRepository.insertBulk(list);
        };
    }

}
