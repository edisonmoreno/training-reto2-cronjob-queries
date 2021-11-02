package com.edisonmoreno.model.repository;


import com.edisonmoreno.model.CronJobMaterialize;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CronJobRepository {
    Flux<CronJobMaterialize> findAll();

    Mono<CronJobMaterialize> findById(String CronJobId);
}
