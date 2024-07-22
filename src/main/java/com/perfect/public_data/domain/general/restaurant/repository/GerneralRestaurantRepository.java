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
        saveBulk("INSERT INTO general_restaurant " +
                        " (id, open_service_name, open_service_id, open_local_goverment_code, manage_id)" +
                        " VALUES (:id, :openServiceName, :openServiceId, :openLocalGovermentCode, :manageId)"
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
}
