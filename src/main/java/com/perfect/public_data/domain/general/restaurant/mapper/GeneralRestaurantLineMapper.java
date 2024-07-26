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
        this.tokenizer.setNames(
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
    );

        this.fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        this.fieldSetMapper.setTargetType(GeneralRestaurantRow.class);
    }

    @Override
    public GeneralRestaurantRow mapLine(String line, int lineNumber) throws Exception {
        if (line.trim().isEmpty()) {
            return null;
        }

        FieldSet fieldSet = tokenizer.tokenize(line);
        return fieldSetMapper.mapFieldSet(fieldSet);
    }
}