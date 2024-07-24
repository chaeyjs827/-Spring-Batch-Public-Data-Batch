package com.perfect.public_data.domain.general.restaurant.repository;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class GerneralRestaurantRepositoryTest {

    @Autowired
    GerneralRestaurantRepository gerneralRestaurantRepository;

    @Test
    void insertBulk() {
        gerneralRestaurantRepository.truncateGeneralRestaurant();
        GeneralRestaurantRow generalRestaurantRow1 = GeneralRestaurantRow.builder()
                .id(1L)
                .build();

        GeneralRestaurantRow generalRestaurantRow2 = GeneralRestaurantRow.builder()
                .id(2L)
                .build();

        List<GeneralRestaurantRow> generalRestaurantRowList = List.of(generalRestaurantRow1, generalRestaurantRow2);

        gerneralRestaurantRepository.insertBulk(generalRestaurantRowList);

        Integer count = gerneralRestaurantRepository.getListCount();

        assertThat(count).isEqualTo(2); // 데이터 2 row 입력 확인
    }

}