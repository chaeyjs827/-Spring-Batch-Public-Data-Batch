package com.perfect.public_data.domain.general.restaurant.partitioner;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeneralRestaurantPartitionerTest {

    @Test
    void test_get_csv_file_row_count() {
        GeneralRestaurantPartitioner partitioner = new GeneralRestaurantPartitioner(1000);
        int lines = partitioner.countLinesInFile();
        System.out.println(lines);
    }

    @Test
    void test_partition() {
        GeneralRestaurantPartitioner partitioner = new GeneralRestaurantPartitioner(1000);
        partitioner.partition(5);
    }
}