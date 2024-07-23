package com.perfect.public_data.general.restaurant.reader;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import org.junit.jupiter.api.Test;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringJUnitConfig
@SpringBootTest
@SpringBatchTest
public class GeneralRestaurantReaderTest {

    @Autowired
    private FlatFileItemReader<GeneralRestaurantRow> reader;

    @Test
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
