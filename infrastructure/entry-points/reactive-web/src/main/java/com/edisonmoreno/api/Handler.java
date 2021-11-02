package com.edisonmoreno.api;

import com.edisonmoreno.model.CronJobMaterialize;
import com.edisonmoreno.usecase.CronJobUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class Handler {
    private final CronJobUseCase cronJobUseCase;

    public Mono<ServerResponse> listAll(ServerRequest serverRequest) {
        Flux<CronJobMaterialize> cronJob = cronJobUseCase.findAll();
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(cronJob, CronJobMaterialize.class);
    }

    public Mono<ServerResponse> listById(ServerRequest serverRequest) {
        String cronJobId = serverRequest.queryParam("cron-job-id").orElse("");
        Mono<CronJobMaterialize> cronJob = cronJobUseCase
                .findById(cronJobId);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(cronJob, CronJobMaterialize.class);
    }

}
