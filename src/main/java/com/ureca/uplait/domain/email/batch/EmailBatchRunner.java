package com.ureca.uplait.domain.email.batch;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class EmailBatchRunner {

    private final JobLauncher jobLauncher;
    private final Job emailSendJob;

    @Async
    public void runEmailBatchAsync(Long planId, String tagIdsStr) {
        try {
            log.info("runEmailBatchRunner 실행");
            jobLauncher.run(
                emailSendJob,
                new JobParametersBuilder()
                    .addString("timestamp", String.valueOf(System.currentTimeMillis()))
                    .addString("planId", planId.toString())
                    .addString("tagIds", tagIdsStr)
                    .toJobParameters()
            );
        } catch (Exception e) {
            log.error("이메일 배치 실행 실패: {}", e.getMessage(), e);
        }
    }
}
