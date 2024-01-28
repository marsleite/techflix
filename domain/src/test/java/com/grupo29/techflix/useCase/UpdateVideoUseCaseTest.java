package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.VideoException;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;

public class UpdateVideoUseCaseTest {

    @Mock
    private VideoRepositoryGateway videoRepositoryGateway;

    private UpdateVideoUseCase updateVideoUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        updateVideoUseCase = new UpdateVideoUseCase(videoRepositoryGateway);
    }
    @Test
    void shouldUpdateVideoSuccessfullyWhenVideoExistsAndValidIdProvided() {
        Video video = new Video();
        Long id = 1L;
        when(videoRepositoryGateway.getVideoById(id)).thenReturn(Mono.just(video));
        when(videoRepositoryGateway.updateVideo(video)).thenReturn(Mono.just(video));

        StepVerifier.create(updateVideoUseCase.execute(video, id))
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    void shouldThrowExceptionWhenUpdateFailsDueToError() {
        Video video = new Video();
        Long id = 1L;
        when(videoRepositoryGateway.getVideoById(id)).thenReturn(Mono.just(video));
        when(videoRepositoryGateway.updateVideo(video)).thenReturn(Mono.error(new RuntimeException()));

        StepVerifier.create(updateVideoUseCase.execute(video, id))
                .expectErrorMatches(throwable -> throwable instanceof VideoException &&
                        throwable.getMessage().equals("Erro ao atualizar o vídeo"))
                .verify();
    }
}
