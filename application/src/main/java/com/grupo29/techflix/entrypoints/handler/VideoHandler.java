package com.grupo29.techflix.entrypoints.handler;

import com.grupo29.techflix.entrypoints.dto.VideoResponse;
import com.grupo29.techflix.model.Video;
import com.grupo29.techflix.useCase.CreateVideoUseCase;
import com.grupo29.techflix.useCase.FindVideoUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class VideoHandler {

    private final CreateVideoUseCase createVideoUseCase;
    private final FindVideoUseCase findVideoUseCase;

    public VideoHandler(CreateVideoUseCase createVideoUseCase, FindVideoUseCase findVideoUseCase) {
        this.createVideoUseCase = createVideoUseCase;
        this.findVideoUseCase = findVideoUseCase;
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

    public Mono<ServerResponse> getVideoById(ServerRequest request) {
        Long requestId = Long.valueOf(request.pathVariable("id"));
        return findVideoUseCase.execute(requestId)
                .flatMap(video -> ServerResponse.ok().body(Mono.just(video), Video.class));
    }
}
