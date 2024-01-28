package com.grupo29.techflix.useCase;

import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;
import com.grupo29.techflix.model.Favorito;
import reactor.core.publisher.Mono;

public class AddFavoritoUseCase {

    private final FavoritoRepositoryGateway favoritoRepositoryGateway;

    public AddFavoritoUseCase(FavoritoRepositoryGateway favoritoRepositoryGateway) {
        this.favoritoRepositoryGateway = favoritoRepositoryGateway;
    }

    public Mono<Favorito> execute(Long idUsuario, Long idVideo) {
        return favoritoRepositoryGateway.adicionarFavorito(idUsuario, idVideo);
    }


}
