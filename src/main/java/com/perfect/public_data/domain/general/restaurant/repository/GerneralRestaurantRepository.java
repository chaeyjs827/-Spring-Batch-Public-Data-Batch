package com.perfect.public_data.domain.general.restaurant.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Repository
public class GerneralRestaurantRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;
    private final ObjectMapper objectMapper;

    public GerneralRestaurantRepository(DataSource dataSource, ObjectMapper objectMapper) {
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.objectMapper = objectMapper;
    }

    public void insertBulk(List<GeneralRestaurantRow> sources) {
        saveBulk(
                "INSERT INTO general_restaurant ( " +
                        "    id, " +
                        "    open_service_name, " +
                        "    open_service_id, " +
                        "    open_local_goverment_code, " +
                        "    management_number, " +
                        "    permission_date, " +
                        "    permission_cancellation_date, " +
                        "    business_status_classification_code, " +
                        "    business_status_name, " +
                        "    detailed_business_status_code, " +
                        "    detailed_business_status_name, " +
                        "    closure_date, " +
                        "    closure_start_date, " +
                        "    closure_end_date, " +
                        "    reopening_date, " +
                        "    location_phone, " +
                        "    location_area, " +
                        "    location_zip_code, " +
                        "    location_full_address, " +
                        "    road_name_full_address, " +
                        "    road_name_zip_code, " +
                        "    business_establishment_name, " +
                        "    last_modified_time, " +
                        "    data_update_classification, " +
                        "    data_update_date, " +
                        "    business_type_classification, " +
                        "    coordinate_information_x, " +
                        "    coordinate_information_y, " +
                        "    sanitation_business_type, " +
                        "    number_of_male_employees, " +
                        "    number_of_female_employees, " +
                        "    business_area_classification, " +
                        "    grade_classification, " +
                        "    water_supply_facility_classification, " +
                        "    total_number_of_employees, " +
                        "    number_of_head_office_employees, " +
                        "    number_of_factory_office_employees, " +
                        "    number_of_factory_sales_employees, " +
                        "    number_of_factory_production_employees, " +
                        "    building_ownership_classification, " +
                        "    deposit_amount, " +
                        "    monthly_rent, " +
                        "    whether_it_is_a_multi_use_establishment, " +
                        "    total_facility_size, " +
                        "    traditional_business_designation_number, " +
                        "    traditional_business_main_food, " +
                        "    homepage, " +
                        "    blank " +
                        ") VALUES ( " +
                        "             :id, " +
                        "             :openServiceName, " +
                        "             :openServiceId, " +
                        "             :openLocalGovermentCode, " +
                        "             :managementNumber, " +
                        "             :permissionDate, " +
                        "             :permissionCancellationDate, " +
                        "             :businessStatusClassificationCode, " +
                        "             :businessStatusName, " +
                        "             :detailedBusinessStatusCode, " +
                        "             :detailedBusinessStatusName, " +
                        "             :closureDate, " +
                        "             :closureStartDate, " +
                        "             :closureEndDate, " +
                        "             :reopeningDate, " +
                        "             :locationPhone, " +
                        "             :locationArea, " +
                        "             :locationZipCode, " +
                        "             :locationFullAddress, " +
                        "             :roadNameFullAddress, " +
                        "             :roadNameZipCode, " +
                        "             :businessEstablishmentName, " +
                        "             :lastModifiedTime, " +
                        "             :dataUpdateClassification, " +
                        "             :dataUpdateDate, " +
                        "             :businessTypeClassification, " +
                        "             :coordinateInformationX, " +
                        "             :coordinateInformationY, " +
                        "             :sanitationBusinessType, " +
                        "             :numberOfMaleEmployees, " +
                        "             :numberOfFemaleEmployees, " +
                        "             :businessAreaClassification, " +
                        "             :gradeClassification, " +
                        "             :waterSupplyFacilityClassification, " +
                        "             :totalNumberOfEmployees, " +
                        "             :numberOfHeadOfficeEmployees, " +
                        "             :numberOfFactoryOfficeEmployees, " +
                        "             :numberOfFactorySalesEmployees, " +
                        "             :numberOfFactoryProductionEmployees, " +
                        "             :buildingOwnershipClassification, " +
                        "             :depositAmount, " +
                        "             :monthlyRent, " +
                        "             :whetherItIsAMultiUseEstablishment, " +
                        "             :totalFacilitySize, " +
                        "             :traditionalBusinessDesignationNumber, " +
                        "             :traditionalBusinessMainFood, " +
                        "             :homepage, " +
                        "             :blank " +
                        "         ); "
                , sources.stream()
                        .map(dto -> toParameterSource(objectMapper, dto))
                        .toArray(MapSqlParameterSource[]::new));
    }

    public MapSqlParameterSource toParameterSource(ObjectMapper objectMapper, Object object) {
        return new MapSqlParameterSource().addValues(objectMapper.convertValue(object, Map.class));
    }

    private void saveBulk(String sql, MapSqlParameterSource[] sources) {
        jdbcTemplate.batchUpdate(sql, sources);
    }

    public void truncateTable(String tableName) {

    }
}
