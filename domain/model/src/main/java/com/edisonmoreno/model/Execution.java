package com.edisonmoreno.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Builder(toBuilder = true)
public class Execution {
    private String state;
    private long duration;
    private Instant date;
    private String httpCode;
}
