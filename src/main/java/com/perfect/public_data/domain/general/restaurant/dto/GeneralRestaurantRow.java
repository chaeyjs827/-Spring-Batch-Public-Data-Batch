package com.perfect.public_data.domain.general.restaurant.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeneralRestaurantRow {

    private Long id;
    private String openServiceName;
    private Long openServiceId;
    private String openLocalGovermentCode;
    private Long manageId;

}
