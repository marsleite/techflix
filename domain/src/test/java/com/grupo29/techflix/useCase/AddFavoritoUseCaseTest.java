package com.grupo29.techflix.useCase;

import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;
import com.grupo29.techflix.model.Favorito;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class AddFavoritoUseCaseTest {
    @Mock
    private FavoritoRepositoryGateway favoritoRepositoryGateway;

    @InjectMocks
    private AddFavoritoUseCase addFavoritoUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should add a favorite when user and video exist")
    public void shouldAddFavoriteWhenUserAndVideoExist() {
        Favorito favorito = Favorito.builder().idVideo(2L).idUsuario(1L).build();

        when(favoritoRepositoryGateway.adicionarFavorito(anyLong(), anyLong())).thenReturn(Mono.just(favorito));

        StepVerifier.create(addFavoritoUseCase.execute(1L, 1L))
                .expectNext(favorito)
                .verifyComplete();
    }
}
