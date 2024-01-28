package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.UsuarioException;
import com.grupo29.techflix.gateway.UsuarioRepositoryGateway;
import reactor.core.publisher.Mono;

public class DeleteUsuarioUseCase {

    private final UsuarioRepositoryGateway usuarioRepositoryGateway;

    public DeleteUsuarioUseCase(UsuarioRepositoryGateway usuarioRepositoryGateway) {
        this.usuarioRepositoryGateway = usuarioRepositoryGateway;
    }

    public Mono<Void> execute(Long id) {
       return usuarioRepositoryGateway.getUsuarioById(id)
               .switchIfEmpty(Mono.error(new UsuarioException("Usuário não encontrado")))
               .doOnSuccess(usuario -> usuarioRepositoryGateway.deleteUsuario(id)).then();
    }
}
