package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.VideoException;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Video;
import reactor.core.publisher.Mono;

public class CreateVideoUseCase {

    private final VideoRepositoryGateway videoRepositoryGateway;

    public CreateVideoUseCase(VideoRepositoryGateway videoRepositoryGateway) {
        this.videoRepositoryGateway = videoRepositoryGateway;
    }

    public Mono<Video> execute(Video video) {
        return videoRepositoryGateway.getVideoById(video.getId())
                .switchIfEmpty(videoRepositoryGateway.createVideo(video))
                .onErrorResume(e -> Mono.error(new VideoException("Erro ao criar o vídeo", e)));
    }
}
