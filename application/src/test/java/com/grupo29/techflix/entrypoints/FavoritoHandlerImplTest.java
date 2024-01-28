package com.grupo29.techflix.entrypoints;

import com.grupo29.techflix.entrypoints.dto.FavoritoRequest;
import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;
import com.grupo29.techflix.integration.IntegrationTest;
import com.grupo29.techflix.model.Favorito;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class FavoritoHandlerImplTest extends IntegrationTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private FavoritoRepositoryGateway favoritoRepositoryGateway;

    @Test
    void testAdicionarFavoritoHandler() {
        FavoritoRequest favoritoRequest = FavoritoRequest.builder()
                .idVideo(1L)
                .build();

        Favorito favorito = Favorito.builder()
                .idVideo(favoritoRequest.getIdVideo())
                .idUsuario(1L)
                .build();

        when(favoritoRepositoryGateway.adicionarFavorito(anyLong(), anyLong())).thenReturn(Mono.just(favorito));

        EntityExchangeResult<Favorito> result = webTestClient.post()
                .uri("/usuarios/1/favoritos")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(favoritoRequest)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Favorito.class)
                .returnResult();

        Favorito favoritoResult = result.getResponseBody();

        assertThat(favoritoResult.getIdVideo()).isEqualTo(favoritoRequest.getIdVideo());
    }

    @Test
    void testRemoverFavoritoHandler() {
        webTestClient.delete()
                .uri("/usuarios/1/favoritos/1")
                .exchange()
                .expectStatus().isOk();
    }
}
