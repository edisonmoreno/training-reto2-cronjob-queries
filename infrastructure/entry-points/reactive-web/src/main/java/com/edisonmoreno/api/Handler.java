package com.edisonmoreno.api;

import com.edisonmoreno.cron.ExecutionRun;
import com.edisonmoreno.model.CronJobMaterialize;
import com.edisonmoreno.usecase.CronJobUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
@RequiredArgsConstructor
public class Handler {
    private final CronJobUseCase cronJobUseCase;

    public Mono<ServerResponse> listAll(ServerRequest serverRequest) {
        Flux<CronJobMaterialize> cronJobFlux = cronJobUseCase.findAll();
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(cronJobFlux, CronJobMaterialize.class);
    }

    public Mono<ServerResponse> listById(ServerRequest serverRequest) {
        String cronJobId = serverRequest.queryParam("cron-job-id").orElse("");
        Mono<CronJobMaterialize> cronJobMono = cronJobUseCase
                .findById(cronJobId);
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(cronJobMono, CronJobMaterialize.class);
    }

    public Mono<ServerResponse> listNextExecution(ServerRequest serverRequest) {
        String cronJobId = serverRequest.queryParam("cron-job-id").orElse("");
        Integer limit = Integer.parseInt(serverRequest.queryParam("limit").orElse("10"));
        Flux<LocalDateTime> cronJobFlux = cronJobUseCase.findById(cronJobId)
                .map(CronJobMaterialize::getCronExpression)
                .map(cronExpression -> ExecutionRun.listNext(cronExpression, limit))
                .flatMapMany(Flux::fromIterable);

        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(cronJobFlux, CronJobMaterialize.class);
    }
}
