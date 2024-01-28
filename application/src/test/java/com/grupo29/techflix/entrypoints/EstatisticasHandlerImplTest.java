package com.grupo29.techflix.entrypoints;

import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.integration.IntegrationTest;
import com.grupo29.techflix.model.Estatisticas;
import com.grupo29.techflix.useCase.EstatisticasUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class EstatisticasHandlerImplTest extends IntegrationTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private VideoRepositoryGateway videoRepositoryGateway;
    @MockBean
    private FavoritoRepositoryGateway favoritoRepositoryGateway;
    @MockBean
    private EstatisticasUseCase estatisticasUseCase;

    @Test
    void testGetEstatisticas() {
        Long videoId = 1L;

        Estatisticas estatisticas = new Estatisticas();
        when(estatisticasUseCase.execute()).thenReturn(Mono.just(estatisticas));

        EntityExchangeResult<Estatisticas> result = webTestClient.get()
                .uri("/estatisticas")
                .exchange()
                .expectStatus().isOk()
                .expectBody(Estatisticas.class)
                .returnResult();

        Estatisticas responseEstatisticas = result.getResponseBody();

        assertThat(responseEstatisticas).isNotNull();

    }
}
