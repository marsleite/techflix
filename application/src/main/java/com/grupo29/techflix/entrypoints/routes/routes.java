package com.grupo29.techflix.entrypoints.routes;

import com.grupo29.techflix.entrypoints.handler.UsuarioHandler;
import com.grupo29.techflix.entrypoints.handler.VideoHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class routes {

    @Bean
    public RouterFunction<ServerResponse> route(
            VideoHandler videoHandler,
            UsuarioHandler usuarioHandler
    ) {
        return RouterFunctions
                .route(POST("/videos"), videoHandler::createVideo)
                .andRoute(GET("/videos/{id}"), videoHandler::getVideoById)
                .andRoute(PUT("/videos/{id}"), videoHandler::updateVideo)
                .andRoute(DELETE("/videos/{id}"), videoHandler::deleteVideo)
                .andRoute(POST("/usuarios"), usuarioHandler::createUsuario)
                .andRoute(GET("/usuarios/{id}"), usuarioHandler::getUsuario)
                .andRoute(PUT("/usuarios/{id}"), usuarioHandler::updateUsuario)
                .andRoute(DELETE("/usuarios/{id}"), usuarioHandler::deleteUsuario);
    }
}
