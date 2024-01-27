package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.VideoException;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CreateVideoUseCaseTest {

    @Mock
    private VideoRepositoryGateway videoRepositoryGateway;

    private CreateVideoUseCase createVideoUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        createVideoUseCase = new CreateVideoUseCase(videoRepositoryGateway);
    }

    @Test
    @DisplayName("Should create video when video does not exist")
    public void shouldCreateVideoWhenVideoDoesNotExist() {
        Video video = new Video();
        when(videoRepositoryGateway.getVideoById(any())).thenReturn(Mono.empty());
        when(videoRepositoryGateway.createVideo(any())).thenReturn(Mono.just(video));

        StepVerifier.create(createVideoUseCase.execute(video))
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should throw VideoException with specific message when error occurs during video creation")
    public void shouldThrowVideoExceptionWithSpecificMessageWhenErrorOccursDuringVideoCreation() {
        Video video = new Video();
        when(videoRepositoryGateway.getVideoById(any())).thenReturn(Mono.empty());

        when(videoRepositoryGateway.createVideo(any()))
                .thenReturn(Mono.error(new VideoException("Erro na criação do vídeo")));

        StepVerifier.create(createVideoUseCase.execute(video))
                .expectErrorSatisfies(throwable -> {
                    assertTrue(throwable instanceof VideoException);
                    assertEquals("Erro ao criar o vídeo", throwable.getMessage());
                })
                .verify();
    }

    @Test
    @DisplayName("Should throw VideoException when error occurs during video creation")
    public void shouldThrowVideoExceptionWhenErrorOccursDuringVideoCreation() {
        Video video = new Video();
        when(videoRepositoryGateway.getVideoById(any())).thenReturn(Mono.empty());
        when(videoRepositoryGateway.createVideo(any())).thenReturn(Mono.error(new RuntimeException()));

        StepVerifier.create(createVideoUseCase.execute(video))
                .expectError(VideoException.class)
                .verify();
    }
}
