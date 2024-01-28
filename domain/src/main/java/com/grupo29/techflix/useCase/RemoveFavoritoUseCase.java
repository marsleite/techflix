package com.grupo29.techflix.useCase;

import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;

public class RemoveFavoritoUseCase {

    private final FavoritoRepositoryGateway favoritoRepositoryGateway;

    public RemoveFavoritoUseCase(FavoritoRepositoryGateway favoritoRepositoryGateway) {
        this.favoritoRepositoryGateway = favoritoRepositoryGateway;
    }

    public void execute(Long idUsuario, Long idVideo) {
        favoritoRepositoryGateway.removerFavorito(idUsuario, idVideo);
    }
}
