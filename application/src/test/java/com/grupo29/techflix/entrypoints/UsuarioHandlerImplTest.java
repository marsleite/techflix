package com.grupo29.techflix.entrypoints;

import com.grupo29.techflix.gateway.UsuarioRepositoryGateway;
import com.grupo29.techflix.integration.IntegrationTest;
import com.grupo29.techflix.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.mockito.ArgumentMatchers.anyLong;

class UsuarioHandlerImplTest extends IntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UsuarioRepositoryGateway usuarioRepositoryGateway;

    @Test
    void testCreateUsuarioHandler() {
        Usuario testUsuario = Usuario.builder()
                .id(1L)
                .nome("Teste usuario")
                .email("test@test.com").build();

        when(usuarioRepositoryGateway.getUsuarioById(anyLong())).thenReturn(Mono.empty());

        when(usuarioRepositoryGateway.createUsuario(any(Usuario.class))).thenReturn(Mono.just(testUsuario));

        EntityExchangeResult<Usuario> result = webTestClient.post()
                .uri("/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(testUsuario)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Usuario.class)
                .returnResult();

        Usuario usuario = result.getResponseBody();

        assertThat(usuario.getId()).isEqualTo(testUsuario.getId());
    }

    @Test
    void testGetUsuarioHandler() {
        Usuario testUsuario = Usuario.builder()
                .id(1L)
                .nome("Teste usuario")
                .email("test@test.com").build();

        when(usuarioRepositoryGateway.getUsuarioById(anyLong())).thenReturn(Mono.just(testUsuario));

        EntityExchangeResult<Usuario> result = webTestClient.get()
                .uri("/usuarios/1")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Usuario.class)
                .returnResult();

        Usuario usuario = result.getResponseBody();

        assertThat(usuario.getId()).isEqualTo(testUsuario.getId());

    }

    @Test
    void testUpdateUsuarioHandler() {
        Usuario testUsuario = Usuario.builder()
                .id(1L)
                .nome("Teste usuario")
                .email("test@test.com").build();

        Usuario testUsuario2 = Usuario.builder()
                .id(1L)
                .nome("Teste usuario 2")
                .email("test2@test.com").build();

        when(usuarioRepositoryGateway.getUsuarioById(anyLong())).thenReturn(Mono.just(testUsuario));

        when(usuarioRepositoryGateway.updateUsuario(any(Usuario.class))).thenReturn(Mono.just(testUsuario2));

        EntityExchangeResult<Usuario> result = webTestClient.put()
                .uri("/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(testUsuario2)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Usuario.class)
                .returnResult();

        Usuario usuario = result.getResponseBody();

        assertThat(usuario.getId()).isEqualTo(testUsuario2.getId());

    }

    @Test
    void testDeleteUsuarioHandler() {
        Usuario testUsuario = Usuario.builder()
                .id(1L)
                .nome("Teste usuario")
                .email("test@test.com").build();

        when(usuarioRepositoryGateway.getUsuarioById(anyLong())).thenReturn(Mono.just(testUsuario));

        when(usuarioRepositoryGateway.deleteUsuario(anyLong())).thenReturn(Mono.empty());

        webTestClient.delete()
                .uri("/usuarios/1")
                .exchange()
                .expectStatus().isOk();

        verify(usuarioRepositoryGateway).deleteUsuario(anyLong());

    }
}
