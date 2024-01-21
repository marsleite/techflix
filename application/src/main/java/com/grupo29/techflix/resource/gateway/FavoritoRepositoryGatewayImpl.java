package com.grupo29.techflix.resource.gateway;

import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;
import com.grupo29.techflix.model.Favorito;
import com.grupo29.techflix.resource.repository.entity.FavoritoEntity;
import com.grupo29.techflix.resource.repository.spring.FavoritoRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class FavoritoRepositoryGatewayImpl implements FavoritoRepositoryGateway {

  private final FavoritoRepositorySpring favoritoRepositorySpring;

  @Autowired
  public FavoritoRepositoryGatewayImpl(FavoritoRepositorySpring favoritoRepositorySpring) {
    this.favoritoRepositorySpring = favoritoRepositorySpring;
  }
  @Override
  public Mono<Favorito> adicionarFavorito(Long idUsuario, Long idVideo) {
    return favoritoRepositorySpring.save(FavoritoEntity.builder()
                    .idUsuario(idUsuario)
                    .idVideo(idVideo)
                    .build())
            .flatMap(favoritoEntity -> Mono.just(favoritoEntity.toDomain()));
  }

  @Override
  public void removerFavorito(Long idUsuario, Long idVideo) {
    favoritoRepositorySpring.deleteById(idVideo);
  }

  @Override
  public Mono<Boolean> checarFavorito(Long idUsuario, Long idVideo) {
    return favoritoRepositorySpring.findById(idVideo)
            .map(favoritoEntity -> favoritoEntity.toDomain().getIdUsuario().equals(idUsuario))
            .defaultIfEmpty(false);
  }
}
