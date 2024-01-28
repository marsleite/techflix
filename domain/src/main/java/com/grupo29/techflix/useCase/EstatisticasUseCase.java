package com.grupo29.techflix.useCase;

import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Estatisticas;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple3;

@AllArgsConstructor
public class EstatisticasUseCase {

    @Autowired
    VideoRepositoryGateway videoRepositoryGateway;
    @Autowired
    FavoritoRepositoryGateway favoritoRepositoryGateway;

    public Mono<Estatisticas> execute() {
        Mono<Long> countVideos = videoRepositoryGateway.getCountVideos()
                .switchIfEmpty(Mono.just(0L));
        Mono<Long> countFavoritos = favoritoRepositoryGateway.getCountFavoritos().switchIfEmpty(Mono.just(0L));
        Mono<Long> averageVisualizacoesVideos = videoRepositoryGateway.getAverageVisualizacoesVideos();

        return Mono.zip(countVideos, countFavoritos, averageVisualizacoesVideos).map(this::mapEstatisticas);
    }

    private Estatisticas mapEstatisticas(Tuple3<Long, Long, Long> objects) {
        return Estatisticas.builder()
                .quantidadeDeVideos(objects.getT1())
                .quantidadeDeVideosFavoritados(objects.getT2())
                .mediaDeVisualizacoes(objects.getT3())
                .build();
    }
}