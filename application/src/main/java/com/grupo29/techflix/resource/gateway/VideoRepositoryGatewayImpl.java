package com.grupo29.techflix.resource.gateway;

import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Video;
import com.grupo29.techflix.resource.repository.entity.VideoEntity;
import com.grupo29.techflix.resource.repository.spring.VideoRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class VideoRepositoryGatewayImpl implements VideoRepositoryGateway {
  private final VideoRepositorySpring videoRepositorySpring;
  @Autowired
  public VideoRepositoryGatewayImpl(VideoRepositorySpring videoRepositorySpring) {
    this.videoRepositorySpring = videoRepositorySpring;
  }

  @Override
  public Mono<Video> getVideoById(Long id) {
    return videoRepositorySpring.findById(id)
            .flatMap(videoEntity -> Mono.just(videoEntity.toDomain()));
  }

  @Override
  public Mono<Video> createVideo(Video video) {
    return videoRepositorySpring.save(VideoEntity.builder()
            .id(video.getId())
            .titulo(video.getTitulo())
            .descricao(video.getDescricao())
            .url(video.getUrl())
            .categoria(video.getCategoria())
            .dataPublicacao(video.getDataPublicacao())
            .build())
            .flatMap(videoEntity -> Mono.just(videoEntity.toDomain()));
  }

  @Override
  public Mono<Video> updateVideo(Video video) {
    return videoRepositorySpring.save(VideoEntity.builder()
            .id(video.getId())
            .titulo(video.getTitulo())
            .descricao(video.getDescricao())
            .url(video.getUrl())
            .categoria(video.getCategoria())
            .dataPublicacao(video.getDataPublicacao())
            .build())
            .flatMap(videoEntity -> Mono.just(videoEntity.toDomain()));
  }

  @Override
  public Mono<Void> deleteVideo(Long id) {
    return videoRepositorySpring.deleteById(id);
  }

  @Override
  public Mono<Long> getCountVideos() {
    return videoRepositorySpring.count();
  }

  @Override
  public Integer getAverageVisualizacoesVideos() {
    return videoRepositorySpring.getAvgVisualizacoes();
  }
}
