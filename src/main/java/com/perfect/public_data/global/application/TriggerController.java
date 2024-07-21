package com.perfect.public_data.global.application;

import com.perfect.public_data.domain.general.restaurant.GeneralRestaurantImportConfig;
import com.perfect.public_data.global.service.JobTriggerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TriggerController {

    private final JobTriggerService jobTriggerService;
    private final GeneralRestaurantImportConfig generalRestaurantImportConfig;

    public TriggerController(JobTriggerService jobTriggerService, GeneralRestaurantImportConfig generalRestaurantImportConfig) {
        this.jobTriggerService = jobTriggerService;
        this.generalRestaurantImportConfig = generalRestaurantImportConfig;
    }

    @PostMapping("/general_restaurant")
    public void runGeneralRestaurantImportConfig() throws Exception {
        jobTriggerService.runJob(generalRestaurantImportConfig.job());
    }
}
