package com.grupo29.techflix.resource.gateway;

import com.grupo29.techflix.model.Usuario;
import com.grupo29.techflix.resource.repository.entity.UsuarioEntity;
import com.grupo29.techflix.resource.repository.spring.UsuarioRepositorySpring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UsuarioRepositoryGatewayImplTest {

  @Mock
  private UsuarioRepositorySpring usuarioRepositorySpring;

  @InjectMocks
  private UsuarioRepositoryGatewayImpl usuarioRepositoryGatewayImpl;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void getUsuarioByIdReturnsUsuario() {
    var usuarioEntity = UsuarioEntity.builder()
            .id(1L)
            .nome("nome")
            .email("email")
            .build();
    when(usuarioRepositorySpring.findById(1L)).thenReturn(Mono.just(usuarioEntity));

    var result = usuarioRepositoryGatewayImpl.getUsuarioById(1L);

    StepVerifier.create(result)
            .assertNext(usuario -> {
              assert usuario.getId().equals(1L);
              assert usuario.getNome().equals("nome");
              assert usuario.getEmail().equals("email");
            })
            .verifyComplete();
  }

  @Test
  public void getUsuarioByIdReturnsEmptyWhenNotFound() {
    when(usuarioRepositorySpring.findById(1L)).thenReturn(Mono.empty());

    var result = usuarioRepositoryGatewayImpl.getUsuarioById(1L);

    StepVerifier.create(result)
            .verifyComplete();
  }

  @Test
  public void createUsuarioReturnsCreatedUsuario() {
    var usuario = new Usuario(1L, "nome", "email");
    var usuarioEntity = UsuarioEntity.fromDomain(usuario);
    when(usuarioRepositorySpring.save(any(UsuarioEntity.class))).thenReturn(Mono.just(usuarioEntity));

    var result = usuarioRepositoryGatewayImpl.createUsuario(usuario);

    StepVerifier.create(result)
            .assertNext(createdUsuario -> {
              assert createdUsuario.getId().equals(usuario.getId());
              assert createdUsuario.getNome().equals(usuario.getNome());
              assert createdUsuario.getEmail().equals(usuario.getEmail());
            })
            .verifyComplete();
  }

  @Test
  public void updateUsuarioReturnsUpdatedUsuario() {
    var usuario = new Usuario(1L, "updated nome", "updated email");
    var usuarioEntity = UsuarioEntity.fromDomain(usuario);
    when(usuarioRepositorySpring.save(any(UsuarioEntity.class))).thenReturn(Mono.just(usuarioEntity));

    var result = usuarioRepositoryGatewayImpl.updateUsuario(usuario);

    StepVerifier.create(result)
            .assertNext(updatedUsuario -> {
              assert updatedUsuario.getId().equals(usuario.getId());
              assert updatedUsuario.getNome().equals(usuario.getNome());
              assert updatedUsuario.getEmail().equals(usuario.getEmail());
            })
            .verifyComplete();
  }

  @Test
  public void deleteUsuarioCallsDeleteById() {
    usuarioRepositoryGatewayImpl.deleteUsuario(1L);

    verify(usuarioRepositorySpring, times(1)).deleteById(1L);
  }
}
