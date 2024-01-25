package com.grupo29.techflix.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Estatisticas {
        Long quantidadeDeVideos;
        Long quantidadeDeVideosFavoritados;
        Integer mediaDeVisualizacoes;
}
