package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.VideoException;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import reactor.core.publisher.Mono;

public class DeleteVideoUseCase {

    private final VideoRepositoryGateway videoRepositoryGateway;

    public DeleteVideoUseCase(VideoRepositoryGateway videoRepositoryGateway) {
        this.videoRepositoryGateway = videoRepositoryGateway;
    }

    public Mono<Void> execute(Long id) {
        return videoRepositoryGateway.getVideoById(id)
                .doOnSuccess(video -> videoRepositoryGateway.deleteVideo(id))
                .switchIfEmpty(Mono.error(new VideoException("Video not found")))
                .then();
    }
}
