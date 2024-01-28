package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.UsuarioException;
import com.grupo29.techflix.gateway.UsuarioRepositoryGateway;
import com.grupo29.techflix.model.Usuario;
import reactor.core.publisher.Mono;

public class UpdateUsuarioUseCase {

    private final UsuarioRepositoryGateway usuarioRepositoryGateway;

    public UpdateUsuarioUseCase(UsuarioRepositoryGateway usuarioRepositoryGateway) {
        this.usuarioRepositoryGateway = usuarioRepositoryGateway;
    }

    public Mono<Usuario> execute(Usuario usuario, Long id) {
        return usuarioRepositoryGateway.getUsuarioById(id)
                .switchIfEmpty(Mono.error(new UsuarioException("Usuário não encontrado")))
                .doOnSuccess(findUsuario -> usuarioRepositoryGateway.updateUsuario(usuario));
    }
}
