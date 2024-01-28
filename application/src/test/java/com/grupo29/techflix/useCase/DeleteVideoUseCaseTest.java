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

import static org.mockito.Mockito.*;

public class DeleteVideoUseCaseTest {

    @Mock
    private VideoRepositoryGateway videoRepositoryGateway;

    private DeleteVideoUseCase deleteVideoUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        deleteVideoUseCase = new DeleteVideoUseCase(videoRepositoryGateway);
    }

    @Test
    public void testExecuteWhenVideoExists() {
        Long id = 1L;
        VideoRepositoryGateway videoRepositoryGateway = mock(VideoRepositoryGateway.class);
        DeleteVideoUseCase deleteVideoUseCase = new DeleteVideoUseCase(videoRepositoryGateway);

        Video video = new Video();
        when(videoRepositoryGateway.getVideoById(id)).thenReturn(Mono.just(video));

        when(videoRepositoryGateway.deleteVideo(id)).thenReturn(Mono.empty());

        Mono<Void> result = deleteVideoUseCase.execute(id);

        StepVerifier.create(result)
                .verifyComplete();

        verify(videoRepositoryGateway, times(1)).getVideoById(id);
        verify(videoRepositoryGateway, times(1)).deleteVideo(id);
    }

    @Test
    public void testExecuteWhenVideoNotFound() {
        Long id = 1L;
        VideoRepositoryGateway videoRepositoryGateway = mock(VideoRepositoryGateway.class);
        DeleteVideoUseCase deleteVideoUseCase = new DeleteVideoUseCase(videoRepositoryGateway);

        when(videoRepositoryGateway.getVideoById(id)).thenReturn(Mono.empty());

        Mono<Void> result = deleteVideoUseCase.execute(id);

        StepVerifier.create(result)
                .expectError(VideoException.class)
                .verify();

        verify(videoRepositoryGateway, times(1)).getVideoById(id);

    }
}
