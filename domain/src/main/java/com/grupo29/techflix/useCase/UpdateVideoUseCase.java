package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.VideoException;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Video;
import reactor.core.publisher.Mono;

public class UpdateVideoUseCase {

    private final VideoRepositoryGateway videoRepositoryGateway;

    public UpdateVideoUseCase(VideoRepositoryGateway videoRepositoryGateway) {
        this.videoRepositoryGateway = videoRepositoryGateway;
    }

    public Mono<Video> execute(Video video, Long id) {
        var videoTest = video;
        var idTest = id;
        return videoRepositoryGateway.getVideoById(idTest)
                .flatMap(videoFound -> {
                    video.setId(videoFound.getId());
                    return videoRepositoryGateway.updateVideo(videoTest);
                })
                .onErrorResume(e -> Mono.error(new VideoException("Erro ao atualizar o vídeo", e)));
    }
}
