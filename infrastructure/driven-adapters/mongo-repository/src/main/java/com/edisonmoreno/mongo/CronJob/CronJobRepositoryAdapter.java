package com.edisonmoreno.mongo.CronJob;

import com.edisonmoreno.model.CronJobMaterialize;
import com.edisonmoreno.mongo.helper.AdapterOperations;
import lombok.extern.slf4j.Slf4j;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class CronJobRepositoryAdapter extends AdapterOperations<CronJobMaterialize, CronJobDocument, String, CronJobMongoRepository> implements com.edisonmoreno.model.repository.CronJobRepository {

    public CronJobRepositoryAdapter(CronJobMongoRepository repository, ObjectMapper mapper) {
        /**
         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model
         */
        super(repository, mapper, d -> mapper.map(d, CronJobMaterialize.class));
    }
}
