package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.UsuarioException;
import com.grupo29.techflix.gateway.UsuarioRepositoryGateway;
import com.grupo29.techflix.model.Usuario;
import reactor.core.publisher.Mono;

public class CreateUsuarioUseCase {

    private final UsuarioRepositoryGateway usuarioRepositoryGateway;

    public CreateUsuarioUseCase(UsuarioRepositoryGateway usuarioRepositoryGateway) {
        this.usuarioRepositoryGateway = usuarioRepositoryGateway;
    }

    public Mono<Usuario> execute(Usuario usuario) {
        return usuarioRepositoryGateway.getUsuarioById(usuario.getId())
                .switchIfEmpty(usuarioRepositoryGateway.createUsuario(usuario))
                .onErrorResume(e -> Mono.error(new UsuarioException("Erro ao criar o usuário", e)));
    }
}
