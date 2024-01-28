package com.grupo29.techflix.entrypoints.handler;

import com.grupo29.techflix.entrypoints.dto.FavoritoRequest;
import com.grupo29.techflix.model.Favorito;
import com.grupo29.techflix.useCase.AddFavoritoUseCase;
import com.grupo29.techflix.useCase.RemoveFavoritoUseCase;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Component
public class FavoritoHandler {

    private final AddFavoritoUseCase addFavoritoUseCase;

    private final RemoveFavoritoUseCase removerFavorito;

    public FavoritoHandler(AddFavoritoUseCase addFavoritoUseCase, RemoveFavoritoUseCase removerFavorito) {
        this.addFavoritoUseCase = addFavoritoUseCase;
        this.removerFavorito = removerFavorito;
    }

    public Mono<ServerResponse> adicionarFavorito(ServerRequest request) {
        Long idUsuario = Long.valueOf(request.pathVariable("idUsuario"));
        return request.bodyToMono(FavoritoRequest.class)
                .flatMap(favoritoRequest -> {
                    Long idVideo = favoritoRequest.getIdVideo();
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(fromPublisher(addFavoritoUseCase.execute(idUsuario, idVideo), Favorito.class));
                });
    }

    public Mono<ServerResponse> removerFavorito(ServerRequest request) {
        Long idUsuario = Long.valueOf(request.pathVariable("idUsuario"));
        Long idVideo = Long.valueOf(request.pathVariable("idVideo"));
        removerFavorito.execute(idUsuario, idVideo);
        return ServerResponse.ok().build();
    }
}
