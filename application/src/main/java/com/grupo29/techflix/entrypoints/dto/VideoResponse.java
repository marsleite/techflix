package com.grupo29.techflix.entrypoints.dto;

import com.grupo29.techflix.model.Categoria;
import com.grupo29.techflix.model.Video;

public class VideoResponse {

    private String title;
    private String description;
    private String url;
    private Categoria categoria;

    public VideoResponse() {
    }

    public VideoResponse(Video video) {
        this.title = video.getTitulo();
        this.description = video.getDescricao();
        this.url = video.getUrl();
        this.categoria = video.getCategoria();
    }

    public static VideoResponse from(Video video) {
        return new VideoResponse(video);
    }
}
