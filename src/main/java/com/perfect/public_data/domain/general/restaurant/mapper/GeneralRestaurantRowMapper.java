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
        row.setOpenServiceId(fieldSet.readString("open_service_id"));
        row.setOpenLocalGovermentCode(fieldSet.readString("open_local_goverment_code"));
        row.setManagementNumber(fieldSet.readString("management_number"));
        row.setPermissionDate(fieldSet.readString("permission_date"));
        row.setPermissionCancellationDate(fieldSet.readString("permission_cancellation_date"));
        row.setBusinessStatusClassificationCode(fieldSet.readString("business_status_classification_code"));
        row.setBusinessStatusName(fieldSet.readString("business_status_name"));
        row.setDetailedBusinessStatusCode(fieldSet.readString("detailed_business_status_code"));
        row.setDetailedBusinessStatusName(fieldSet.readString("detailed_business_status_name"));
        row.setClosureDate(fieldSet.readString("closure_date"));
        row.setClosureStartDate(fieldSet.readString("closure_start_date"));
        row.setClosureEndDate(fieldSet.readString("closure_end_date"));
        row.setReopeningDate(fieldSet.readString("reopening_date"));
        row.setLocationPhone(fieldSet.readString("location_phone"));
        row.setLocationArea(fieldSet.readString("location_area"));
        row.setLocationZipCode(fieldSet.readString("location_zip_code"));
        row.setLocationFullAddress(fieldSet.readString("location_full_address"));
        row.setRoadNameFullAddress(fieldSet.readString("road_name_full_address"));
        row.setRoadNameZipCode(fieldSet.readString("road_name_zip_code"));
        row.setBusinessEstablishmentName(fieldSet.readString("business_establishment_name"));
        row.setLastModifiedTime(fieldSet.readString("last_modified_time"));
        row.setDataUpdateClassification(fieldSet.readString("data_update_classification"));
        row.setDataUpdateDate(fieldSet.readString("data_update_date"));
        row.setBusinessTypeClassification(fieldSet.readString("business_type_classification"));
        row.setCoordinateInformationX(fieldSet.readString("coordinate_information_x"));
        row.setCoordinateInformationY(fieldSet.readString("coordinate_information_y"));
        row.setSanitationBusinessType(fieldSet.readString("sanitation_business_type"));
        row.setNumberOfMaleEmployees(fieldSet.readString("number_of_male_employees"));
        row.setNumberOfFemaleEmployees(fieldSet.readString("number_of_female_employees"));
        row.setBusinessAreaClassification(fieldSet.readString("business_area_classification"));
        row.setGradeClassification(fieldSet.readString("grade_classification"));
        row.setWaterSupplyFacilityClassification(fieldSet.readString("water_supply_facility_classification"));
        row.setTotalNumberOfEmployees(fieldSet.readString("total_number_of_employees"));
        row.setNumberOfHeadOfficeEmployees(fieldSet.readString("number_of_head_office_employees"));
        row.setNumberOfFactoryOfficeEmployees(fieldSet.readString("number_of_factory_office_employees"));
        row.setNumberOfFactorySalesEmployees(fieldSet.readString("number_of_factory_sales_employees"));
        row.setNumberOfFactoryProductionEmployees(fieldSet.readString("number_of_factory_production_employees"));
        row.setBuildingOwnershipClassification(fieldSet.readString("building_ownership_classification"));
        row.setDepositAmount(fieldSet.readString("deposit_amount"));
        row.setMonthlyRent(fieldSet.readString("monthly_rent"));
        row.setWhetherItIsAMultiUseEstablishment(fieldSet.readString("whether_it_is_a_multi_use_establishment"));
        row.setTotalFacilitySize(fieldSet.readString("total_facility_size"));
        row.setTraditionalBusinessDesignationNumber(fieldSet.readString("traditional_business_designation_number"));
        row.setTraditionalBusinessMainFood(fieldSet.readString("traditional_business_main_food"));
        row.setHomepage(fieldSet.readString("homepage"));
        row.setBlank(fieldSet.readString("blank"));
        return row;
    }
}