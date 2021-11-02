package com.edisonmoreno.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;


@Configuration
public class RouterRest {
    @Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/cronjob/list"), handler::listAll)
                .andRoute(GET("/api/cronjob/by-id"), handler::listById)
                .andRoute(GET("/api/cronjob/list-next"), handler::listNextExecution);

    }
}
