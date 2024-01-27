package com.grupo29.techflix.entrypoints.handler;

import com.grupo29.techflix.model.Video;
import com.grupo29.techflix.useCase.CreateVideoUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class VideoHandler {

    private final CreateVideoUseCase createVideoUseCase;

    public VideoHandler(CreateVideoUseCase createVideoUseCase) {
        this.createVideoUseCase = createVideoUseCase;
    }

    public Mono<ServerResponse> createVideo(ServerRequest request) {
        return request.bodyToMono(Video.class)
                .flatMap(video -> {
                    if (video == null) {
                        return ServerResponse.badRequest().build();
                    }

                    return createVideoUseCase.execute(video)
                            .flatMap(videoCreated ->
                                    ServerResponse.ok().body(Mono.just(videoCreated), Video.class)
                            );
                });
    }
}
