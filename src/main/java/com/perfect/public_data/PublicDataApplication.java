package com.perfect.public_data;

import com.perfect.public_data.domain.general.restaurant.GeneralRestaurantImportConfig;
import com.perfect.public_data.global.service.JobTriggerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PublicDataApplication {

    private final JobTriggerService jobTriggerService;
    private final GeneralRestaurantImportConfig generalRestaurantImportConfig;

    public PublicDataApplication(JobTriggerService jobTriggerService, GeneralRestaurantImportConfig generalRestaurantImportConfig) {
        this.jobTriggerService = jobTriggerService;
        this.generalRestaurantImportConfig = generalRestaurantImportConfig;
    }

    public static void main(String[] args) {
        SpringApplication.run(PublicDataApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void runJob() throws Exception {
        jobTriggerService.runJob(generalRestaurantImportConfig.job());
    }
}
