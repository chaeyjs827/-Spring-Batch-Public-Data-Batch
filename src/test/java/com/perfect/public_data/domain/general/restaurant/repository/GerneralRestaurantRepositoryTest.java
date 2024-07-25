package com.perfect.public_data.domain.general.restaurant.repository;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GerneralRestaurantRepositoryTest {

    @Autowired
    GerneralRestaurantRepository gerneralRestaurantRepository;

    @BeforeEach
    public void setUp() throws Exception {
        gerneralRestaurantRepository.truncateGeneralRestaurant();
    }

    @Test
    @DisplayName("general_restaurant 테이블 truncate 테스트")
    void test_truncate_general_restaurant_table() {
        insertBulk();

        Integer insertedCount = gerneralRestaurantRepository.getListCount();
        assertThat(insertedCount).isEqualTo(2); // 데이터 2 row 입력 확인

        gerneralRestaurantRepository.truncateGeneralRestaurant();
        Integer truncatedCount = gerneralRestaurantRepository.getListCount();
        assertThat(truncatedCount).isEqualTo(0); // row 0 확인
    }

    @Test
    @DisplayName("일반 음식점 데이터 bulk insert 테스트")
    void test_insert_bulk_to_general_restaurant() {
        insertBulk();

        Integer count = gerneralRestaurantRepository.getListCount();
        assertThat(count).isEqualTo(2); // 데이터 2 row 입력 확인

    }

    private void insertBulk() {
        GeneralRestaurantRow generalRestaurantRow1 = GeneralRestaurantRow.builder()
                .id(1L)
                .build();

        GeneralRestaurantRow generalRestaurantRow2 = GeneralRestaurantRow.builder()
                .id(2L)
                .build();

        List<GeneralRestaurantRow> generalRestaurantRowList = List.of(generalRestaurantRow1, generalRestaurantRow2);

        gerneralRestaurantRepository.insertBulk(generalRestaurantRowList);
    }

}