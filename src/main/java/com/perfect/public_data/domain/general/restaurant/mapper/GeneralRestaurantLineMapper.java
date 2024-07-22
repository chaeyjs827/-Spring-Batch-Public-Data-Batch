package com.perfect.public_data.domain.general.restaurant.mapper;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;

public class GeneralRestaurantLineMapper implements LineMapper<GeneralRestaurantRow> {

    private final DelimitedLineTokenizer tokenizer;
    private final BeanWrapperFieldSetMapper<GeneralRestaurantRow> fieldSetMapper;

    public GeneralRestaurantLineMapper() {
        this.tokenizer = new DelimitedLineTokenizer();
        this.tokenizer.setNames("id", "open_service_name", "open_service_id", "open_local_goverment_code", "manage_id");

        this.fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        this.fieldSetMapper.setTargetType(GeneralRestaurantRow.class);
    }

    @Override
    public GeneralRestaurantRow mapLine(String line, int lineNumber) throws Exception {
        if (line == null || line.trim().isEmpty()) {
            return null; // 빈 줄이나 파일 끝을 처리
        }

        FieldSet fieldSet = tokenizer.tokenize(line);
        return fieldSetMapper.mapFieldSet(fieldSet);
    }
}