package com.ureca.uplait.domain.admin.api;

import com.ureca.uplait.domain.plan.entity.Plan;
import com.ureca.uplait.global.exception.GlobalException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import static com.ureca.uplait.global.response.ResultCode.FAST_API_DB_ERROR;

@Component
@RequiredArgsConstructor
public class FastAPIClient {

    private final WebClient fastApiWebClient;

    public void saveVector(Plan plan, String description) {
        fastApiWebClient.post()
            .uri(uriBuilder -> uriBuilder
                .path("/vector")
                .queryParam("plan_id", plan.getId())
                .queryParam("description", description)
                .build()
            )
            .retrieve()
            .onStatus(status -> !status.is2xxSuccessful(), clientResponse -> clientResponse.bodyToMono(String.class)
                .flatMap(errorBody -> Mono.error(new GlobalException(FAST_API_DB_ERROR))))
            .bodyToMono(Void.class)
            .block();
    }
}
