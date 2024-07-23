package com.perfect.public_data.domain.general.restaurant.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GeneralRestaurantRow {

    private Long id;
    private String openServiceName;
    private String openServiceId;
    private String openLocalGovermentCode;
    private String managementNumber;
    private String permissionDate;
    private String permissionCancellationDate;
    private String businessStatusClassificationCode;
    private String businessStatusName;
    private String detailedBusinessStatusCode;
    private String detailedBusinessStatusName;
    private String closureDate;
    private String closureStartDate;
    private String closureEndDate;
    private String reopeningDate;
    private String locationPhone;
    private String locationArea;
    private String locationZipCode;
    private String locationFullAddress;
    private String roadNameFullAddress;
    private String roadNameZipCode;
    private String businessEstablishmentName;
    private String lastModifiedTime;
    private String dataUpdateClassification;
    private String dataUpdateDate;
    private String businessTypeClassification;
    private String coordinateInformationX;
    private String coordinateInformationY;
    private String sanitationBusinessType;
    private String numberOfMaleEmployees;
    private String numberOfFemaleEmployees;
    private String businessAreaClassification;
    private String gradeClassification;
    private String waterSupplyFacilityClassification;
    private String totalNumberOfEmployees;
    private String numberOfHeadOfficeEmployees;
    private String numberOfFactoryOfficeEmployees;
    private String numberOfFactorySalesEmployees;
    private String numberOfFactoryProductionEmployees;
    private String buildingOwnershipClassification;
    private String depositAmount;
    private String monthlyRent;
    private String whetherItIsAMultiUseEstablishment;
    private String totalFacilitySize;
    private String traditionalBusinessDesignationNumber;
    private String traditionalBusinessMainFood;
    private String homepage;
    private String blank;

    @Builder
    public GeneralRestaurantRow(Long id, String openServiceName, String openServiceId, String openLocalGovermentCode, String managementNumber, String permissionDate, String permissionCancellationDate, String businessStatusClassificationCode, String businessStatusName, String detailedBusinessStatusCode, String detailedBusinessStatusName, String closureDate, String closureStartDate, String closureEndDate, String reopeningDate, String locationPhone, String locationArea, String locationZipCode, String locationFullAddress, String roadNameFullAddress, String roadNameZipCode, String businessEstablishmentName, String lastModifiedTime, String dataUpdateClassification, String dataUpdateDate, String businessTypeClassification, String coordinateInformationX, String coordinateInformationY, String sanitationBusinessType, String numberOfMaleEmployees, String numberOfFemaleEmployees, String businessAreaClassification, String gradeClassification, String waterSupplyFacilityClassification, String totalNumberOfEmployees, String numberOfHeadOfficeEmployees, String numberOfFactoryOfficeEmployees, String numberOfFactorySalesEmployees, String numberOfFactoryProductionEmployees, String buildingOwnershipClassification, String depositAmount, String monthlyRent, String whetherItIsAMultiUseEstablishment, String totalFacilitySize, String traditionalBusinessDesignationNumber, String traditionalBusinessMainFood, String homepage, String blank) {
        this.id = id;
        this.openServiceName = openServiceName;
        this.openServiceId = openServiceId;
        this.openLocalGovermentCode = openLocalGovermentCode;
        this.managementNumber = managementNumber;
        this.permissionDate = permissionDate;
        this.permissionCancellationDate = permissionCancellationDate;
        this.businessStatusClassificationCode = businessStatusClassificationCode;
        this.businessStatusName = businessStatusName;
        this.detailedBusinessStatusCode = detailedBusinessStatusCode;
        this.detailedBusinessStatusName = detailedBusinessStatusName;
        this.closureDate = closureDate;
        this.closureStartDate = closureStartDate;
        this.closureEndDate = closureEndDate;
        this.reopeningDate = reopeningDate;
        this.locationPhone = locationPhone;
        this.locationArea = locationArea;
        this.locationZipCode = locationZipCode;
        this.locationFullAddress = locationFullAddress;
        this.roadNameFullAddress = roadNameFullAddress;
        this.roadNameZipCode = roadNameZipCode;
        this.businessEstablishmentName = businessEstablishmentName;
        this.lastModifiedTime = lastModifiedTime;
        this.dataUpdateClassification = dataUpdateClassification;
        this.dataUpdateDate = dataUpdateDate;
        this.businessTypeClassification = businessTypeClassification;
        this.coordinateInformationX = coordinateInformationX;
        this.coordinateInformationY = coordinateInformationY;
        this.sanitationBusinessType = sanitationBusinessType;
        this.numberOfMaleEmployees = numberOfMaleEmployees;
        this.numberOfFemaleEmployees = numberOfFemaleEmployees;
        this.businessAreaClassification = businessAreaClassification;
        this.gradeClassification = gradeClassification;
        this.waterSupplyFacilityClassification = waterSupplyFacilityClassification;
        this.totalNumberOfEmployees = totalNumberOfEmployees;
        this.numberOfHeadOfficeEmployees = numberOfHeadOfficeEmployees;
        this.numberOfFactoryOfficeEmployees = numberOfFactoryOfficeEmployees;
        this.numberOfFactorySalesEmployees = numberOfFactorySalesEmployees;
        this.numberOfFactoryProductionEmployees = numberOfFactoryProductionEmployees;
        this.buildingOwnershipClassification = buildingOwnershipClassification;
        this.depositAmount = depositAmount;
        this.monthlyRent = monthlyRent;
        this.whetherItIsAMultiUseEstablishment = whetherItIsAMultiUseEstablishment;
        this.totalFacilitySize = totalFacilitySize;
        this.traditionalBusinessDesignationNumber = traditionalBusinessDesignationNumber;
        this.traditionalBusinessMainFood = traditionalBusinessMainFood;
        this.homepage = homepage;
        this.blank = blank;
    }
}