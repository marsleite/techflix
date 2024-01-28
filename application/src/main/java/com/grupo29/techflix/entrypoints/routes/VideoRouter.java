package com.grupo29.techflix.entrypoints.routes;

import com.grupo29.techflix.entrypoints.handler.VideoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class VideoRouter {

    @Bean
    public RouterFunction<ServerResponse> route(VideoHandler handler) {
        return RouterFunctions
                .route(POST("/videos"), handler::createVideo)
                .andRoute(GET("/videos/{id}"), handler::getVideoById)
                .andRoute(PUT("/videos/{id}"), handler::updateVideo);
    }
}
