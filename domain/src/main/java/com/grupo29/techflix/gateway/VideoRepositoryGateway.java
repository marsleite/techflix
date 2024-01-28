package com.grupo29.techflix.gateway;

import com.grupo29.techflix.model.Categoria;
import com.grupo29.techflix.model.Video;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VideoRepositoryGateway {

    Mono<Video> getVideoById(Long id);

    Mono<Video> createVideo(Video video);

    Mono<Video> updateVideo(Video video);

    Mono<Void> deleteVideo(Long id);

    Mono<Long> getCountVideos();

    Mono<Long> getAverageVisualizacoesVideos();

    Flux<Video> getVideosByCategoria(Categoria categoria);

    Flux<Video> getVideosByTitulo(String titulo);

    Flux<Video> getAllVideos();

}
