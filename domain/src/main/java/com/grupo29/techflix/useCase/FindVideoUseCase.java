package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.VideoException;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Video;
import reactor.core.publisher.Mono;

public class FindVideoUseCase {

    private final VideoRepositoryGateway videoRepositoryGateway;

    public FindVideoUseCase(VideoRepositoryGateway videoRepositoryGateway) {
        this.videoRepositoryGateway = videoRepositoryGateway;
    }

    public Mono<Video> execute(Long id) {
        return videoRepositoryGateway.getVideoById(id)
                .onErrorResume(e -> Mono.error(new VideoException("Erro ao buscar o vídeo", e)));
    }
}
