package com.edisonmoreno.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CronJobMaterialize {
    private String id;
    private String name;
    private String url;
    private String cronExpression;
    private String timeout;
    private String retry;
    private String email;
    private Integer totalSuccessful;
    private Integer totalFailed;
    private Set<Execution> executions;

    private CronJobMaterialize() {
    }
}
