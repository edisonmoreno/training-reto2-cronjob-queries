package com.edisonmoreno.usecase;

import com.edisonmoreno.model.CronJobMaterialize;
import com.edisonmoreno.model.repository.CronJobRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class CronJobUseCase {
    private final CronJobRepository repository;

    public CronJobUseCase(CronJobRepository repository) {
        this.repository = repository;
    }

    public Flux<CronJobMaterialize> findAll() {
        return repository.findAll();
    }

    public Mono<CronJobMaterialize> findById(String cronJobId) {
        return repository.findById(cronJobId);
    }
}
