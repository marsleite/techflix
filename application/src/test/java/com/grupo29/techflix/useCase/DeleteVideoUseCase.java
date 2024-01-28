package com.grupo29.techflix.useCase;

import com.grupo29.techflix.gateway.VideoRepositoryGateway;

public class DeleteVideoUseCase {

    private final VideoRepositoryGateway videoRepositoryGateway;

    public DeleteVideoUseCase(VideoRepositoryGateway videoRepositoryGateway) {
        this.videoRepositoryGateway = videoRepositoryGateway;
    }

    public void execute(Long id) {
        videoRepositoryGateway.getVideoById(id)
                .doOnSuccess(video -> videoRepositoryGateway.deleteVideo(id));
    }
}
