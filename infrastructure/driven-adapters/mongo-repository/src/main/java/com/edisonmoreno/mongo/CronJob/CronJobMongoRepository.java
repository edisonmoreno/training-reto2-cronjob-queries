package com.edisonmoreno.mongo.CronJob;

import com.edisonmoreno.model.CronJobMaterialize;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface CronJobMongoRepository extends ReactiveMongoRepository<CronJobDocument, String>, ReactiveQueryByExampleExecutor<CronJobDocument> {
}
