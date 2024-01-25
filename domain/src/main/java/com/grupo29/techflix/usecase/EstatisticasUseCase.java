package com.grupo29.techflix.usecase;

import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.model.Estatisticas;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
public class EstatisticasUseCase {
    @Autowired
    VideoRepositoryGateway videoRepositoryGateway;
    @Autowired
    FavoritoRepositoryGateway favoritoRepositoryGateway;

    public Estatisticas listarEstatisticas() {
        Long countVideos = videoRepositoryGateway.getCountVideos().block();
        Long countFavoritos = favoritoRepositoryGateway.getCountFavoritos().block();
        Integer averageVisualizacoesVideos = videoRepositoryGateway.getAverageVisualizacoesVideos();

        return Estatisticas.builder()
                .quantidadeDeVideos(countVideos)
                .quantidadeDeVideosFavoritados(countFavoritos)
                .mediaDeVisualizacoes(averageVisualizacoesVideos)
                .build();
    }
}