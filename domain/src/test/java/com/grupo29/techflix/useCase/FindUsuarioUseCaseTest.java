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
import static org.mockito.Mockito.when;

public class FindUsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryGateway usuarioRepositoryGateway;

    @InjectMocks
    private FindUsuarioUseCase findUsuarioUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return the user when user exists")
    public void shouldReturnTheUserWhenUserExists() {
        Usuario usuario = Usuario.builder()
                .id(123L)
                .nome("Teste")
                .email("test@tes.com")
                .build();

        when(usuarioRepositoryGateway.getUsuarioById(any(Long.class))).thenReturn(Mono.just(usuario));

        StepVerifier.create(findUsuarioUseCase.execute(123L))
                .expectNext(usuario)
                .verifyComplete();
    }

    @Test
    @DisplayName("Should throw UsuarioException when user does not exist")
    public void shouldThrowUsuarioExceptionWhenUserDoesNotExist() {
        when(usuarioRepositoryGateway.getUsuarioById(any(Long.class))).thenReturn(Mono.empty());

        StepVerifier.create(findUsuarioUseCase.execute(123L))
                .expectError(UsuarioException.class)
                .verify();
    }
}
