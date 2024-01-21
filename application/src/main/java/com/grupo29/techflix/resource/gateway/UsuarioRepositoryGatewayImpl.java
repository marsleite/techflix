package com.grupo29.techflix.resource.gateway;

import com.grupo29.techflix.gateway.UsuarioRepositoryGateway;
import com.grupo29.techflix.model.Usuario;
import com.grupo29.techflix.resource.repository.entity.UsuarioEntity;
import com.grupo29.techflix.resource.repository.spring.UsuarioRepositorySpring;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UsuarioRepositoryGatewayImpl implements UsuarioRepositoryGateway {


  private final UsuarioRepositorySpring usuarioRepositorySpring;

  @Autowired
  public UsuarioRepositoryGatewayImpl(UsuarioRepositorySpring usuarioRepositorySpring) {
    this.usuarioRepositorySpring = usuarioRepositorySpring;
  }

  @Override
  public Mono<Usuario> createUsuario(Usuario usuario) {
    return usuarioRepositorySpring.save(UsuarioEntity.builder()
        .id(usuario.getId())
        .nome(usuario.getNome())
        .email(usuario.getEmail())
        .build())
        .flatMap(usuarioEntity -> Mono.just(usuarioEntity.toDomain()));
  }

  @Override
  public Mono<Usuario> getUsuarioById(Long id) {
    return usuarioRepositorySpring.findById(id)
        .flatMap(usuarioEntity -> Mono.just(usuarioEntity.toDomain()));
  }

  @Override
  public Mono<Usuario> updateUsuario(Usuario usuario) {
    return usuarioRepositorySpring.save(UsuarioEntity.builder()
        .id(usuario.getId())
        .nome(usuario.getNome())
        .email(usuario.getEmail())
        .build())
        .flatMap(usuarioEntity -> Mono.just(usuarioEntity.toDomain()));
  }

  @Override
  public void deleteUsuario(Long id) {
    usuarioRepositorySpring.deleteById(id);
  }
}
