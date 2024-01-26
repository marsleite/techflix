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
        Video video = request.bodyToMono(Video.class).block();

        if (video == null) {
            return ServerResponse.badRequest().build();
        }

        Video videoCreated = createVideoUseCase.execute(video).block();

        return ServerResponse.ok().body(Mono.just(videoCreated), Video.class);
    }
}
