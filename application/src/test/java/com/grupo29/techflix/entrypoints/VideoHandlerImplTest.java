package com.grupo29.techflix.entrypoints;

import com.grupo29.techflix.exception.VideoException;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.integration.IntegrationTest;
import com.grupo29.techflix.model.Categoria;
import com.grupo29.techflix.model.Video;
import com.grupo29.techflix.useCase.DeleteVideoUseCase;
import com.grupo29.techflix.useCase.FindVideoUseCase;
import com.grupo29.techflix.useCase.UpdateVideoUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class VideoHandlerImplTest extends IntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private VideoRepositoryGateway videoRepositoryGateway;

    @MockBean
    private FindVideoUseCase findVideoUseCase;

    @MockBean
    private UpdateVideoUseCase updateVideoUseCase;

    @MockBean
    private DeleteVideoUseCase deleteVideoUseCase;

    @Test
    void testCreateVideoHandler() {
        Video testVideo = Video.builder()
                .id(1L)
                .titulo("Teste video")
                .url("http/grupo29.com")
                .categoria(Categoria.ANIMACAO)
                .descricao("testando o video")
                .build();

        when(videoRepositoryGateway.getVideoById(anyLong())).thenReturn(Mono.empty());

        when(videoRepositoryGateway.createVideo(any(Video.class))).thenReturn(Mono.just(testVideo));

        EntityExchangeResult<Video> result = webTestClient.post()
                .uri("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(testVideo)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Video.class)
                .returnResult();

        Video videoCreated = result.getResponseBody();

        assertThat(videoCreated).isNotNull();
        assertThat(videoCreated.getTitulo()).isEqualTo(testVideo.getTitulo());
    }

    @Test
    void testCreateVideoHandlerError() {
        Video testVideo = Video.builder()
                .id(1L)
                .titulo("Teste video")
                .categoria(Categoria.ANIMACAO)
                .descricao("testando o video")
                .build();

        when(videoRepositoryGateway.getVideoById(anyLong())).thenReturn(Mono.empty());
        when(videoRepositoryGateway.createVideo(any(Video.class))).thenThrow(new VideoException("Erro ao criar o vídeo"));

        webTestClient.post()
                .uri("/videos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(testVideo)
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void testGetVideoById() {
        Long videoId = 1L;

        Video video = new Video(/* atributos do vídeo */);
        when(findVideoUseCase.execute(any())).thenReturn(Mono.just(video));

        EntityExchangeResult<Video> result = webTestClient.get()
                .uri("/videos/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Video.class)
                .returnResult();

        Video videoResponse = result.getResponseBody();

        assertThat(videoResponse).isNotNull();

    }

    @Test
    void shouldDeleteVideoWhenValidId() {
        Long videoId = 1L;
        when(deleteVideoUseCase.execute(anyLong())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/videos/1")
                .exchange()
                .expectStatus().isOk();
    }

    @Test
    void shouldReturnServerErrorWhenDeleteVideoFails() {
        Long videoId = 1L;
        when(deleteVideoUseCase.execute(anyLong())).thenThrow(new VideoException("Error deleting video"));

        webTestClient.delete()
                .uri("/videos/1")
                .exchange()
                .expectStatus().is5xxServerError();
    }

    @Test
    void shouldUpdateVideoWhenValidRequest() {
        Long videoId = 1L;
        Video video = new Video(/* attributes of the video */);
        when(updateVideoUseCase.execute(any(), anyLong())).thenReturn(Mono.just(video));

        EntityExchangeResult<Video> result = webTestClient.put()
                .uri("/videos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(video)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Video.class)
                .returnResult();

        Video videoResponse = result.getResponseBody();

        assertThat(videoResponse).isNotNull();
    }

    @Test
    void shouldReturnServerErrorWhenUpdateVideoFails() {
        Long videoId = 1L;
        Video video = new Video(/* attributes of the video */);
        when(updateVideoUseCase.execute(any(), anyLong())).thenThrow(new VideoException("Error updating video"));

        webTestClient.put()
                .uri("/videos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(video)
                .exchange()
                .expectStatus().is5xxServerError();
    }
}
