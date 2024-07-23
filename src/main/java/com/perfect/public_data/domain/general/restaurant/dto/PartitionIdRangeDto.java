package com.perfect.public_data.domain.general.restaurant.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PartitionIdRangeDto {

    private Long minId;
    private Long maxId;

    public PartitionIdRangeDto(Long minId, Long maxId) {
        this.minId = minId;
        this.maxId = maxId;
    }
}
