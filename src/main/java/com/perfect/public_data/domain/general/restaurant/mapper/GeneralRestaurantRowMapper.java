package com.perfect.public_data.domain.general.restaurant.mapper;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class GeneralRestaurantRowMapper implements FieldSetMapper<GeneralRestaurantRow> {

    @Override
    public GeneralRestaurantRow mapFieldSet(FieldSet fieldSet) throws BindException {
        GeneralRestaurantRow row = new GeneralRestaurantRow();
        row.setId(fieldSet.readLong("id"));
        row.setOpenServiceName(fieldSet.readString("open_service_name"));
        row.setOpenServiceId(fieldSet.readLong("open_service_id"));
        row.setOpenLocalGovermentCode(fieldSet.readString("open_local_goverment_code"));
        row.setManageId(fieldSet.readLong("manage_id"));
        return row;
    }
}