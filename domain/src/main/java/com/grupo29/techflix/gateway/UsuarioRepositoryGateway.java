package com.grupo29.techflix.gateway;

import com.grupo29.techflix.model.Usuario;
import reactor.core.publisher.Mono;

public interface UsuarioRepositoryGateway {
  Mono<Usuario> createUsuario(Usuario usuario);

  Mono<Usuario> getUsuarioById(Long id);

  Mono<Usuario> updateUsuario(Usuario usuario);

  Mono<Void> deleteUsuario(Long id);
}
