package com.grupo29.techflix.useCase;

import com.grupo29.techflix.exception.UsuarioException;
import com.grupo29.techflix.gateway.UsuarioRepositoryGateway;
import com.grupo29.techflix.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class CreateUsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryGateway usuarioRepositoryGateway;

    @InjectMocks
    private CreateUsuarioUseCase createUsuarioUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should create a new user when user does not exist")
    public void shouldCreateNewUserWhenUserDoesNotExist() {
        Usuario usuario = Usuario.builder().id(1L).build();

        when(usuarioRepositoryGateway.getUsuarioById(anyLong())).thenReturn(Mono.empty());
        when(usuarioRepositoryGateway.createUsuario(any(Usuario.class))).thenReturn(Mono.just(usuario));

        StepVerifier.create(createUsuarioUseCase.execute(usuario))
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should throw UsuarioException when there is an error creating the user")
    public void shouldThrowUsuarioExceptionWhenThereIsAnErrorCreatingTheUser() {
        Usuario usuario = Usuario.builder().id(1L).build();

        when(usuarioRepositoryGateway.getUsuarioById(anyLong())).thenReturn(Mono.empty());
        when(usuarioRepositoryGateway.createUsuario(any(Usuario.class))).thenReturn(Mono.error(new RuntimeException()));

        StepVerifier.create(createUsuarioUseCase.execute(usuario))
                .expectError(UsuarioException.class)
                .verify();
    }
}
