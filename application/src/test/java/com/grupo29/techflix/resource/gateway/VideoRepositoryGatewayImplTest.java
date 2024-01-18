package com.grupo29.techflix.resource.gateway;

import com.grupo29.techflix.model.Categoria;
import com.grupo29.techflix.model.Video;
import com.grupo29.techflix.resource.repository.entity.VideoEntity;
import com.grupo29.techflix.resource.repository.spring.VideoRepositorySpring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class VideoRepositoryGatewayImplTest {

  @Mock
  private VideoRepositorySpring videoRepositorySpring;

  @InjectMocks
  private VideoRepositoryGatewayImpl videoRepositoryGatewayImpl;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void getVideoByIdReturnsVideo() {
    var videoEntity = VideoEntity.builder()
            .id(1L)
            .titulo("titulo")
            .descricao("descricao")
            .url("url")
            .categoria(Categoria.AVENTURA)
            .dataPublicacao(LocalDateTime.now())
            .build();
    when(videoRepositorySpring.findById(1L)).thenReturn(Mono.just(videoEntity));

    var result = videoRepositoryGatewayImpl.getVideoById(1L);

    StepVerifier.create(result)
            .assertNext(video -> {
              assert video.getId().equals(1L);
              assert video.getTitulo().equals("titulo");
              assert video.getDescricao().equals("descricao");
              assert video.getUrl().equals("url");
              assert video.getCategoria().equals(Categoria.AVENTURA);
            })
            .verifyComplete();
  }

  @Test
  public void getVideoByIdReturnsEmptyWhenNotFound() {
    when(videoRepositorySpring.findById(1L)).thenReturn(Mono.empty());

    var result = videoRepositoryGatewayImpl.getVideoById(1L);

    StepVerifier.create(result)
            .verifyComplete();
  }

  @Test
  public void createVideoReturnsCreatedVideo() {
    var video = new Video(1L, "titulo", "descricao", "url", Categoria.AVENTURA, LocalDateTime.now());
    var videoEntity = VideoEntity.fromDomain(video);
    when(videoRepositorySpring.save(any(VideoEntity.class))).thenReturn(Mono.just(videoEntity));

    var result = videoRepositoryGatewayImpl.createVideo(video);

    StepVerifier.create(result)
            .assertNext(createdVideo -> {
              assert createdVideo.getId().equals(video.getId());
              assert createdVideo.getTitulo().equals(video.getTitulo());
              assert createdVideo.getDescricao().equals(video.getDescricao());
              assert createdVideo.getUrl().equals(video.getUrl());
              assert createdVideo.getCategoria().equals(video.getCategoria());
            })
            .verifyComplete();
  }

  @Test
  public void updateVideoReturnsUpdatedVideo() {
    var video = new Video(1L, "updated titulo", "updated descricao", "updated url", Categoria.AVENTURA, LocalDateTime.now());
    var videoEntity = VideoEntity.fromDomain(video);
    when(videoRepositorySpring.save(any(VideoEntity.class))).thenReturn(Mono.just(videoEntity));

    var result = videoRepositoryGatewayImpl.updateVideo(video);

    StepVerifier.create(result)
            .assertNext(updatedVideo -> {
              assert updatedVideo.getId().equals(video.getId());
              assert updatedVideo.getTitulo().equals(video.getTitulo());
              assert updatedVideo.getDescricao().equals(video.getDescricao());
              assert updatedVideo.getUrl().equals(video.getUrl());
              assert updatedVideo.getCategoria().equals(video.getCategoria());
            })
            .verifyComplete();
  }

  @Test
  public void deleteVideoCallsDeleteById() {
    videoRepositoryGatewayImpl.deleteVideo(1L);

    verify(videoRepositorySpring, times(1)).deleteById(1L);
  }
}
