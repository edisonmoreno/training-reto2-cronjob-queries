package com.edisonmoreno.cron;

import org.springframework.scheduling.support.CronExpression;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public final class ExecutionRun {
    private static ExecutionRun executionRun;
    private static Logger logger = Logger.getLogger("ExecutionRun");

    private ExecutionRun() {
        super();
    }

    public static synchronized ExecutionRun instance() {
        if (ExecutionRun.executionRun == null) {
            ExecutionRun.executionRun = new ExecutionRun();
        }
        return ExecutionRun.executionRun;
    }

    public static List<LocalDateTime> listNext(String currentCron, Integer limit) {
        CronExpression cronTrigger = CronExpression.parse(currentCron);
        AtomicReference<LocalDateTime> localDateTime = new AtomicReference<>(LocalDateTime.now());

        return IntStream.range(0, limit)
                .mapToObj(value -> {
                    localDateTime.set(cronTrigger.next(localDateTime.get()));
                    return cronTrigger.next(localDateTime.get());
                })
                .collect(Collectors.toList());
    }
}