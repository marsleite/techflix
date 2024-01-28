package com.grupo29.techflix.entrypoints.handler;

import com.grupo29.techflix.entrypoints.dto.VideoResponse;
import com.grupo29.techflix.model.Video;
import com.grupo29.techflix.useCase.CreateVideoUseCase;
import com.grupo29.techflix.useCase.FindVideoUseCase;
import com.grupo29.techflix.useCase.UpdateVideoUseCase;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class VideoHandler {

    private final CreateVideoUseCase createVideoUseCase;
    private final FindVideoUseCase findVideoUseCase;

    private final UpdateVideoUseCase updateVideoUseCase;

    public VideoHandler(
            CreateVideoUseCase createVideoUseCase,
            FindVideoUseCase findVideoUseCase,
            UpdateVideoUseCase updateVideoUseCase
    ) {
        this.createVideoUseCase = createVideoUseCase;
        this.findVideoUseCase = findVideoUseCase;
        this.updateVideoUseCase = updateVideoUseCase;
    }

    public Mono<ServerResponse> createVideo(ServerRequest request) {
        return request.bodyToMono(Video.class)
                .flatMap(video -> {
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

    public Mono<ServerResponse> updateVideo(ServerRequest request) {
        Long requestId = Long.valueOf(request.pathVariable("id"));
        return request.bodyToMono(Video.class)
                .flatMap(video -> {
                    return updateVideoUseCase.execute(video, requestId)
                            .flatMap(videoCreated ->
                                    ServerResponse.ok().body(Mono.just(videoCreated), Video.class)
                            );
                });
    }
}
