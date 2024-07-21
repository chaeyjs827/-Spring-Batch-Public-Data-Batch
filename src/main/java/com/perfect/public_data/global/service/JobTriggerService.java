package com.perfect.public_data.global.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JobTriggerService {

    private final JobLauncher jobLauncher;

    public JobTriggerService(JobLauncher jobLauncher) {
        this.jobLauncher = jobLauncher;
    }

    public void runJob(Job targetJob) {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("random-value", System.currentTimeMillis())
                .toJobParameters();
        runJob(targetJob, jobParameters);
    }

    private void runJob(Job targetJob, JobParameters jobParameters) {
        try {
            jobLauncher.run(targetJob, jobParameters);
        } catch (Exception e) {
            log.error("{} job 스케줄 등록에 실패했습니다.", targetJob.getName());
            log.error(e.getMessage(), e);
            throw new IllegalStateException("");
        }
    }
}
