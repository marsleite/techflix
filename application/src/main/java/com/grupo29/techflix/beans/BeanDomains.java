package com.grupo29.techflix.beans;

import com.grupo29.techflix.gateway.FavoritoRepositoryGateway;
import com.grupo29.techflix.gateway.UsuarioRepositoryGateway;
import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.useCase.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanDomains {

    @Bean
    public CreateVideoUseCase createVideo(VideoRepositoryGateway videoRepositoryGateway) {
        return new CreateVideoUseCase(videoRepositoryGateway);
    }

    @Bean
    public FindVideoUseCase getvideo(VideoRepositoryGateway videoRepositoryGateway) {
        return new FindVideoUseCase(videoRepositoryGateway);
    }

    @Bean
    public UpdateVideoUseCase updateVideo(VideoRepositoryGateway videoRepositoryGateway) {
        return new UpdateVideoUseCase(videoRepositoryGateway);
    }

    @Bean
    public DeleteVideoUseCase deleteVideo(VideoRepositoryGateway videoRepositoryGateway) {
        return new DeleteVideoUseCase(videoRepositoryGateway);
    }

    @Bean
    public CreateUsuarioUseCase createUsuario(UsuarioRepositoryGateway usuarioRepositoryGateway) {
        return new CreateUsuarioUseCase(usuarioRepositoryGateway);
    }

    @Bean
    public FindUsuarioUseCase getUsuario(UsuarioRepositoryGateway usuarioRepositoryGateway) {
        return new FindUsuarioUseCase(usuarioRepositoryGateway);
    }

    @Bean
    public UpdateUsuarioUseCase updateUsuario(UsuarioRepositoryGateway usuarioRepositoryGateway) {
        return new UpdateUsuarioUseCase(usuarioRepositoryGateway);
    }

    @Bean
    public DeleteUsuarioUseCase deleteUsuario(UsuarioRepositoryGateway usuarioRepositoryGateway) {
        return new DeleteUsuarioUseCase(usuarioRepositoryGateway);
    }

    @Bean
    public EstatisticasUseCase getEstatisticas(VideoRepositoryGateway videoRepositoryGateway,
                                            FavoritoRepositoryGateway favoritoRepositoryGateway) {
        return new EstatisticasUseCase(videoRepositoryGateway, favoritoRepositoryGateway);
    }
}
