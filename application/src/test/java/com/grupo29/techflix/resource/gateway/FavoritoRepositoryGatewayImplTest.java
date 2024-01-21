package com.grupo29.techflix.resource.gateway;

import com.grupo29.techflix.resource.repository.entity.FavoritoEntity;
import com.grupo29.techflix.resource.repository.spring.FavoritoRepositorySpring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoritoRepositoryGatewayImplTest {

  @Mock
  private FavoritoRepositorySpring favoritoRepositorySpring;

  @InjectMocks
  private FavoritoRepositoryGatewayImpl favoritoRepositoryGatewayImpl;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void adicionarFavoritoReturnsAddedFavorito() {
    var favoritoEntity = FavoritoEntity.builder()
            .idUsuario(1L)
            .idVideo(1L)
            .build();
    when(favoritoRepositorySpring.save(any(FavoritoEntity.class))).thenReturn(Mono.just(favoritoEntity));

    var result = favoritoRepositoryGatewayImpl.adicionarFavorito(1L, 1L);

    StepVerifier.create(result)
            .assertNext(favorito -> {
              assert favorito.getIdUsuario().equals(1L);
              assert favorito.getIdVideo().equals(1L);
            })
            .verifyComplete();
  }

  @Test
  public void removerFavoritoCallsDeleteById() {
    favoritoRepositoryGatewayImpl.removerFavorito(1L, 1L);

    verify(favoritoRepositorySpring, times(1)).deleteById(1L);
  }

  @Test
  public void checarFavoritoReturnsTrueWhenFavoritoExists() {
    var favoritoEntity = FavoritoEntity.builder()
            .idUsuario(1L)
            .idVideo(1L)
            .build();
    when(favoritoRepositorySpring.findById(1L)).thenReturn(Mono.just(favoritoEntity));

    var result = favoritoRepositoryGatewayImpl.checarFavorito(1L, 1L);

    StepVerifier.create(result)
            .assertNext(isFavorito -> {
              assert isFavorito;
            })
            .verifyComplete();
  }

  @Test
  public void checarFavoritoReturnsFalseWhenFavoritoDoesNotExist() {
    when(favoritoRepositorySpring.findById(1L)).thenReturn(Mono.empty());

    var result = favoritoRepositoryGatewayImpl.checarFavorito(1L, 1L);

    StepVerifier.create(result)
            .assertNext(isFavorito -> {
              assert !isFavorito;
            }).verifyComplete();
  }
}
