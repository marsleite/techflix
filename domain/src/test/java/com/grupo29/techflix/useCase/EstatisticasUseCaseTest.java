package com.grupo29.techflix.useCase;

import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Estatisticas;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class EstatisticasUseCaseTest {

    @Mock
    VideoRepositoryGateway videoRepositoryGateway;
    @Mock
    FavoritoRepositoryGateway favoritoRepositoryGateway;

    EstatisticasUseCase estatisticasUseCase;

    AutoCloseable mocks;

    @BeforeEach
    void setup() {
        mocks = MockitoAnnotations.openMocks(this);
        estatisticasUseCase = new EstatisticasUseCase(videoRepositoryGateway, favoritoRepositoryGateway);
    }

    @AfterEach
    void tearDown() throws Exception {
        mocks.close();
    }

    @Test
    void deveListarEstatisticas() {
        when(videoRepositoryGateway.getCountVideos()).thenReturn(Mono.just(10L));
        when(videoRepositoryGateway.getAverageVisualizacoesVideos()).thenReturn(Mono.just(20L));
        when(favoritoRepositoryGateway.getCountFavoritos()).thenReturn(Mono.just(10L));

        Estatisticas estatisticas = estatisticasUseCase.execute().block();

        assertThat(estatisticas.getMediaDeVisualizacoes()).isEqualTo(20L);
        assertThat(estatisticas.getQuantidadeDeVideos()).isEqualTo(10L);
        assertThat(estatisticas.getQuantidadeDeVideosFavoritados()).isEqualTo(10L);
    }
}
