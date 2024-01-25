package com.grupo29.techflix.resource.repository.entity;

import com.grupo29.techflix.model.Categoria;
import com.grupo29.techflix.model.Video;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Builder
@Table("video")
public class VideoEntity {

    @Id
    private Long id;
    private String titulo;
    private String descricao;
    private String url;
    private Categoria categoria;
    private LocalDateTime dataPublicacao;
    private Integer visualizacoes;

    public Video toDomain() {
        return Video.builder()
                .id(this.id)
                .titulo(this.titulo)
                .descricao(this.descricao)
                .url(this.url)
                .categoria(this.categoria)
                .dataPublicacao(this.dataPublicacao)
                .visualizacoes(this.visualizacoes)
                .build();
    }

    public static VideoEntity fromDomain(Video video) {
        return VideoEntity.builder()
                .id(video.getId())
                .titulo(video.getTitulo())
                .descricao(video.getDescricao())
                .url(video.getUrl())
                .categoria(video.getCategoria())
                .dataPublicacao(video.getDataPublicacao())
                .visualizacoes(video.getVisualizacoes())
                .build();
    }
}
