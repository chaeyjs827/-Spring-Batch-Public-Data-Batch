package com.perfect.public_data.domain.general.restaurant.step;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import com.perfect.public_data.domain.general.restaurant.repository.GerneralRestaurantRepository;
import com.perfect.public_data.global.enums.FileFullPathEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.MetaDataInstanceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.transaction.PlatformTransactionManager;

import static org.mockito.Mockito.*;

import javax.sql.DataSource;

import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;


@SpringBootTest
public class GeneralRestaurantReadAndSaveStepTest {

    @Autowired
    private GeneralRestaurantReadAndSaveStep generalRestaurantReadAndSaveStep;

    @Autowired
    private GerneralRestaurantRepository gerneralRestaurantRepository;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private DataSource dataSource;

    @BeforeEach
    void setUp() {
        generalRestaurantReadAndSaveStep = new GeneralRestaurantReadAndSaveStep(jobRepository, platformTransactionManager, dataSource, gerneralRestaurantRepository);
    }

    @Test
    @DisplayName("reader 정상 작동 테스트")
    void test_reader_successfully() throws Exception {
        FlatFileItemReader<GeneralRestaurantRow> reader = generalRestaurantReadAndSaveStep.reader();
        reader.open(MetaDataInstanceFactory.createStepExecution().getExecutionContext());

        GeneralRestaurantRow row;
        while ((row = reader.read()) != null) {
            assertNotNull(row);
        }
        reader.close();
    }

    @Test
    void readerShouldThrowExceptionIfFileNotFound() {
        assertThrows(FileNotFoundException.class, () -> generalRestaurantReadAndSaveStep.reader());
    }

    @Test
    void writerShouldWorkCorrectly() throws Exception {
        List<GeneralRestaurantRow> rows = Collections.singletonList(new GeneralRestaurantRow());
        ItemWriter<GeneralRestaurantRow> writer = generalRestaurantReadAndSaveStep.writer();

        writer.write((Chunk<? extends GeneralRestaurantRow>) rows);

        verify(gerneralRestaurantRepository, times(1)).insertBulk(rows);
    }

    @Test
    void writerShouldHandleFailure() {
        List<GeneralRestaurantRow> rows = Collections.singletonList(new GeneralRestaurantRow());
        ItemWriter<GeneralRestaurantRow> writer = generalRestaurantReadAndSaveStep.writer();

        doThrow(new RuntimeException("DB error")).when(gerneralRestaurantRepository).insertBulk(any());

        assertThrows(RuntimeException.class, () -> writer.write((Chunk<? extends GeneralRestaurantRow>) rows));
    }
}