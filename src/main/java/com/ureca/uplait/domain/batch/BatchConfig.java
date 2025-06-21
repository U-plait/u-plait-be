package com.ureca.uplait.domain.batch;

import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.domain.plan.repository.PlanRepository;
import com.ureca.uplait.domain.user.entity.User;
import com.ureca.uplait.domain.user.repository.UserRepository;
import com.ureca.uplait.global.exception.GlobalException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.data.domain.Sort;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.ureca.uplait.global.response.ResultCode.PLAN_NOT_FOUND;

@Slf4j
@EnableAsync
@Configuration
public class BatchConfig {

    @Bean(name = "batchTaskExecutor")
    public TaskExecutor batchTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);
        executor.setMaxPoolSize(4);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("EmailBatch-");
        executor.initialize();
        return executor;
    }

    @Bean
    public Job emailSendJob(JobRepository jobRepository, Step sendStep) {
        return new JobBuilder("emailSendJob", jobRepository)
            .start(sendStep)
            .build();
    }

    @Bean
    public Step sendStep(JobRepository jobRepository,
                         PlatformTransactionManager transactionManager,
                         RepositoryItemReader<User> userReader,
                         ItemProcessor<User, MimeMessage> emailProcessor,
                         ItemWriter<MimeMessage> emailWriter) {

        return new StepBuilder("sendStep", jobRepository)
            .<User, MimeMessage>chunk(20, transactionManager)
            .reader(userReader)
            .processor(emailProcessor)
            .writer(emailWriter)
            .build();
    }

    @Bean
    @StepScope
    public RepositoryItemReader<User> userReader(
            UserRepository userRepository,
            @Value("#{jobParameters['planId']}") Long planId  // 배치 실행 시 외부에서 주입
    ) {
        RepositoryItemReader<User> reader = new RepositoryItemReader<>();
        reader.setRepository(userRepository);
        reader.setMethodName("findUsersWithMatchingTopTagsByPlanId");
        reader.setPageSize(100);
        reader.setArguments(List.of(planId)); // <- 여기 중요!!
        reader.setSort(Map.of("id", Sort.Direction.ASC));
        return reader;
    }

    @Bean
    @StepScope
    public ItemProcessor<User, MimeMessage> emailProcessor(
        JavaMailSender mailSender,
        @Value("#{jobParameters['planId']}") String planIdStr,
        @Value("#{jobParameters['tagIds']}") String tagIdsStr,
        PlanRepository planRepository
    ) {
        return user -> {
            // Plan 조회
            Long planId = Long.parseLong(planIdStr);
            Plan plan = planRepository.findById(planId)
                .orElseThrow(() -> new GlobalException(PLAN_NOT_FOUND));

            // tagIds 파싱 및 조회
            List<Long> tagIds = Arrays.stream(tagIdsStr.split(","))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .map(Long::parseLong)
                .collect(Collectors.toList());
//            List<com.ureca.uplait.domain.user.entity.Tag> tags = tagRepository.findAllById(tagIds);

            // 이메일 생성
            Email email = EmailTemplateUtil.buildEmail(user, plan);

            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");
            helper.setTo(user.getEmail());
            helper.setSubject(email.getTitle());
            helper.setText(email.getContent());

            return message;
        };
    }

    @Bean
    public ItemWriter<MimeMessage> emailWriter(JavaMailSender mailSender) {
        return messages -> {
            for (MimeMessage msg : messages) {
                try {
                    mailSender.send(msg);
                    log.info("[이메일 발송 성공] to={}", (Object) msg.getAllRecipients());
                } catch (Exception e) {
                    log.error("[이메일 발송 실패] to={}, reason={}", (Object) msg.getAllRecipients(), e.getMessage(), e);
                }
            }
        };
    }
}
