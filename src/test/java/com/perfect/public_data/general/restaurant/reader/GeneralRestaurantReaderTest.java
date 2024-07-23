package com.perfect.public_data.general.restaurant.reader;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import com.perfect.public_data.domain.general.restaurant.step.GeneralRestaurantReadAndSaveStep;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.PlatformTransactionManager;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringJUnitConfig
@SpringBootTest
@SpringBatchTest
public class GeneralRestaurantReaderTest {

    @Autowired
    private FlatFileItemReader<GeneralRestaurantRow> reader;

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private PlatformTransactionManager platformTransactionManager;

    @Autowired
    private GeneralRestaurantReadAndSaveStep stepConfig;

    @Test
    @DisplayName("GeneralRestaurantReadAndSaveStep 정상 참조 확인")
    public void testStepConfiguration() throws Exception {
        Step step = stepConfig.step();

        assertThat(step.getName()).isEqualTo("generalRestaurantReadAndSaveStep");
    }

    @Test
    @DisplayName("첫 번째 데이터 id값 확인(=1)")
    public void testReader() throws Exception {
        // Given
        reader.open(new ExecutionContext());

        // When
        GeneralRestaurantRow item = reader.read();

        // Then
        assertNotNull(item);
        assertEquals(item.getId(), 1);  // 첫 번째 element
    }


}
