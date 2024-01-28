package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.VideoException;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Video;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class FindVideoUseCaseTest {

    private VideoRepositoryGateway videoRepositoryGateway;
    private FindVideoUseCase findVideoUseCase;

    @BeforeEach
    public void setup() {
        videoRepositoryGateway = Mockito.mock(VideoRepositoryGateway.class);
        findVideoUseCase = new FindVideoUseCase(videoRepositoryGateway);
    }

    @Test
    public void findVideoByIdReturnsVideoWhenVideoExists() {
        Video video = new Video();
        when(videoRepositoryGateway.getVideoById(anyLong())).thenReturn(Mono.just(video));

        StepVerifier.create(findVideoUseCase.execute(1L))
                .expectNext(video)
                .verifyComplete();
    }

    @Test
    public void findVideoByIdThrowsVideoExceptionWhenVideoDoesNotExist() {
        when(videoRepositoryGateway.getVideoById(anyLong())).thenReturn(Mono.error(new RuntimeException()));

        StepVerifier.create(findVideoUseCase.execute(1L))
                .expectError(VideoException.class)
                .verify();
    }
}
