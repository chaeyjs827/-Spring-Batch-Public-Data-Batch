package com.perfect.public_data.domain.general.restaurant.step;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import com.perfect.public_data.domain.general.restaurant.mapper.GeneralRestaurantLineMapper;
import com.perfect.public_data.domain.general.restaurant.partitioner.GeneralRestaurantPartitioner;
import com.perfect.public_data.domain.general.restaurant.policy.GeneralRestaurantSkipPolicy;
import com.perfect.public_data.domain.general.restaurant.repository.GerneralRestaurantRepository;
import com.perfect.public_data.global.util.CsvUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.core.partition.support.TaskExecutorPartitionHandler;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Configuration
@Slf4j
public class GeneralRestaurantReadAndSaveStep implements StepExecutionListener {

    private static final String STEP_NAME = "generalRestaurantReadAndSaveStep";

    private static final Integer CHUNK_SIZE = 1000;
    private static final Integer PARTITION_SIZE = 500;
    private static final Integer GRID_SIZE_DEFAULT = 20; // total Gri

    private final JobRepository jobRepository;
    private final PlatformTransactionManager platformTransactionManager;
    private final GerneralRestaurantRepository gerneralRestaurantRepository;

    public GeneralRestaurantReadAndSaveStep(
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
    public Step stepManager() throws Exception {
        return new StepBuilder(STEP_NAME, jobRepository)
                .partitioner(STEP_NAME, partitioner())
                .step(step())
                .partitionHandler(partitionHandler())
                .build();
    }

    @JobScope
    public Step step() throws IOException {
        return new StepBuilder(STEP_NAME, jobRepository)
                .<GeneralRestaurantRow, GeneralRestaurantRow> chunk(CHUNK_SIZE, platformTransactionManager)
                .reader(reader(null, null))
                .writer(writer())
                .build();
    }

    @StepScope
    private Partitioner partitioner() {
        return new GeneralRestaurantPartitioner(PARTITION_SIZE);
    }

    private TaskExecutorPartitionHandler partitionHandler() throws IOException {
        TaskExecutorPartitionHandler handler = new TaskExecutorPartitionHandler();
        handler.setTaskExecutor(taskExecutor());
        handler.setStep(step());
        handler.setGridSize(GRID_SIZE_DEFAULT);
        return handler;
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setQueueCapacity(50);
        executor.initialize();
        return executor;
    }

    @Bean(STEP_NAME + "reader")
    @StepScope
    public FlatFileItemReader<GeneralRestaurantRow> reader(
            @Value("#{stepExecutionContext['startLine'] ?: 1}") Integer startLine,
            @Value("#{stepExecutionContext['endLine']}") Integer endLine)  throws IOException {
        gerneralRestaurantRepository.truncateGeneralRestaurant();
        Resource resource = CsvUtil.getGeneralRestaurantCsv();

        if (!resource.exists()) {
            throw new FileNotFoundException("파일이 존재하지 않습니다. : " + resource.getFilename());
        }
//
//        BufferedReader reader = new BufferedReader(
//                new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)
//        );

        return new FlatFileItemReaderBuilder<GeneralRestaurantRow>()
                .name("personItemReader")
                .delimited()
                .names(new String[]{
                        "id",
                        "openServiceName",
                        "openServiceId",
                        "openLocalGovermentCode",
                        "managementNumber",
                        "permissionDate",
                        "permissionCancellationDate",
                        "businessStatusClassificationCode",
                        "businessStatusName",
                        "detailedBusinessStatusCode",
                        "detailedBusinessStatusName",
                        "closureDate",
                        "closureStartDate",
                        "closureEndDate",
                        "reopeningDate",
                        "locationPhone",
                        "locationArea",
                        "locationZipCode",
                        "locationFullAddress",
                        "roadNameFullAddress",
                        "roadNameZipCode",
                        "businessEstablishmentName",
                        "lastModifiedTime",
                        "dataUpdateClassification",
                        "dataUpdateDate",
                        "businessTypeClassification",
                        "coordinateInformationX",
                        "coordinateInformationY",
                        "sanitationBusinessType",
                        "numberOfMaleEmployees",
                        "numberOfFemaleEmployees",
                        "businessAreaClassification",
                        "gradeClassification",
                        "waterSupplyFacilityClassification",
                        "totalNumberOfEmployees",
                        "numberOfHeadOfficeEmployees",
                        "numberOfFactoryOfficeEmployees",
                        "numberOfFactorySalesEmployees",
                        "numberOfFactoryProductionEmployees",
                        "buildingOwnershipClassification",
                        "depositAmount",
                        "monthlyRent",
                        "whetherItIsAMultiUseEstablishment",
                        "totalFacilitySize",
                        "traditionalBusinessDesignationNumber",
                        "traditionalBusinessMainFood",
                        "homepage",
                        "blank"
                })
                .lineMapper(new GeneralRestaurantLineMapper())
                .resource(resource)
                .linesToSkip(startLine - 1)
                .maxItemCount(endLine - startLine + 1)
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

    @Bean
    public GeneralRestaurantSkipPolicy customSkipPolicy() {
        return new GeneralRestaurantSkipPolicy();
    }

}
