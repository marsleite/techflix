package com.grupo29.techflix.entrypoints.handler;

import com.grupo29.techflix.model.Estatisticas;
import com.grupo29.techflix.useCase.EstatisticasUseCase;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class EstatisticasHandler {

    private EstatisticasUseCase estatisticasUseCase;

    public Mono<ServerResponse> getEstatisticas(ServerRequest request) {
        return estatisticasUseCase.execute()
                .flatMap(estatisticas -> ServerResponse.ok().body(Mono.just(estatisticas), Estatisticas.class));
    }
}
