package com.grupo29.techflix.useCase;

import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Categoria;
import com.grupo29.techflix.model.Video;
import reactor.core.publisher.Flux;

public class FiltrosUseCase {
    private final VideoRepositoryGateway videoRepositoryGateway;

    public FiltrosUseCase(VideoRepositoryGateway videoRepositoryGateway) {
        this.videoRepositoryGateway = videoRepositoryGateway;
    }

    public Flux<Video> getVideosByCategoria(Categoria categoria) {
        return videoRepositoryGateway.getVideosByCategoria(categoria);
    }

    public Flux<Video> getVideosByTitulo(String titulo) {
        return videoRepositoryGateway.getVideosByTitulo(titulo);
    }

    public Flux<Video> getAllVideos() {
        return videoRepositoryGateway.getAllVideos();
    }
}
