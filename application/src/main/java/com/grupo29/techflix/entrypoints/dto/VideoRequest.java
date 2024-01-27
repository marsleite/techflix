package com.grupo29.techflix.entrypoints.dto;

import com.grupo29.techflix.model.Categoria;
import com.grupo29.techflix.model.Video;
import lombok.Getter;

@Getter
public class VideoRequest {
    private String titulo;
    private String descricao;
    private String url;
    private String categoria;

    public Video toVideo() {
        Categoria categoriaEnum = Categoria.valueOf(categoria.toUpperCase());
        return Video.builder()
                .titulo(titulo)
                .descricao(descricao)
                .url(url)
                .categoria(categoriaEnum)
                .build();
    }
}
