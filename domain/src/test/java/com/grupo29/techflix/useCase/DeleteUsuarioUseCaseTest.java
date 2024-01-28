package com.grupo29.techflix.useCase;

import com.grupo29.techflix.gateway.UsuarioRepositoryGateway;
import com.grupo29.techflix.model.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class DeleteUsuarioUseCaseTest {

    @Mock
    private UsuarioRepositoryGateway usuarioRepositoryGateway;

    @InjectMocks
    private DeleteUsuarioUseCase deleteUsuarioUseCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldDeleteTheUserWhenUserExists() {
        Usuario usuario = Usuario.builder()
                .id(123L)
                .nome("Teste")
                .email("test@test.com")
                .build();

        when(usuarioRepositoryGateway.getUsuarioById(anyLong())).thenReturn(Mono.just(usuario));
        when(usuarioRepositoryGateway.deleteUsuario(anyLong())).thenReturn(Mono.empty());

        StepVerifier.create(deleteUsuarioUseCase.execute(123L))
                .verifyComplete();

        Mockito.verify(usuarioRepositoryGateway).deleteUsuario(123L);

    }
}
