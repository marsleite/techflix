package com.grupo29.techflix.entrypoints.handler;

import com.grupo29.techflix.entrypoints.dto.VideoRequest;
import com.grupo29.techflix.model.Categoria;
import com.grupo29.techflix.model.Video;
import com.grupo29.techflix.useCase.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class VideoHandler {

    private final CreateVideoUseCase createVideoUseCase;
    private final FindVideoUseCase findVideoUseCase;
    private final UpdateVideoUseCase updateVideoUseCase;
    private final DeleteVideoUseCase deleteVideoUseCase;
    private final FiltrosUseCase filtrosUseCase;

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

    public Mono<ServerResponse> deleteVideo(ServerRequest request) {
        Long requestId = Long.valueOf(request.pathVariable("id"));
        deleteVideoUseCase.execute(requestId);
        return ServerResponse.ok().build();
    }

    public Mono<ServerResponse> getVideosByCategoria(ServerRequest request) {
        Categoria categoria = Categoria.valueOf(request.bodyToMono(VideoRequest.class).block().getCategoria().toUpperCase());
        return filtrosUseCase.getVideosByCategoria(categoria)
                .collectList()
                .flatMap(videos -> ServerResponse.ok().bodyValue(videos));
    }

    public Mono<ServerResponse> getVideosByTitulo(ServerRequest request) {
        String titulo = request.bodyToMono(VideoRequest.class).block().getTitulo();
        return filtrosUseCase.getVideosByTitulo(titulo)
                .collectList()
                .flatMap(videos -> ServerResponse.ok().bodyValue(videos));
    }

    public Mono<ServerResponse> getAllVideos(ServerRequest request) {
        return filtrosUseCase.getAllVideos()
                .collectList()
                .flatMap(videos -> ServerResponse.ok().bodyValue(videos));
    }
}
