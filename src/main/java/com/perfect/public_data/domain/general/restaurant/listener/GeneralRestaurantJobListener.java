package com.perfect.public_data.domain.general.restaurant.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@Component
public class GeneralRestaurantJobListener implements JobExecutionListener {

    private LocalDateTime startTime;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = LocalDateTime.now();
        log.info("[Job started] : {}", startTime.format(formatter));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        LocalDateTime endTime = LocalDateTime.now();
        Duration duration = Duration.between(startTime, endTime);
        log.info("[Job ended at]: {}", endTime.format(formatter));
        log.info("[Job duration]: {} ms", duration.toMillis());
    }

}
