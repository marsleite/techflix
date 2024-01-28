package com.grupo29.techflix.gateway;

import com.grupo29.techflix.model.Video;
import reactor.core.publisher.Mono;

public interface VideoRepositoryGateway {

  Mono<Video> getVideoById(Long id);

  Mono<Video> createVideo(Video video);

  Mono<Video> updateVideo(Video video);

  Mono<Void> deleteVideo(Long id);
}
