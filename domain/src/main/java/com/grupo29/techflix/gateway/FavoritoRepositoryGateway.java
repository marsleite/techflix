package com.grupo29.techflix.gateway;

import com.grupo29.techflix.model.Favorito;
import reactor.core.publisher.Mono;

public interface FavoritoRepositoryGateway {
    Mono<Favorito> adicionarFavorito(Long idUsuario, Long idFilme);

    void removerFavorito(Long idUsuario, Long idFilme);

    Mono<Boolean> checarFavorito(Long idUsuario, Long idFilme);

    Mono<Long> getCountFavoritos();
}
