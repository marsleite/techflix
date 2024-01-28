package com.grupo29.techflix.beans;

import com.grupo29.techflix.gateway.VideoRepositoryGateway;
import com.grupo29.techflix.useCase.CreateVideoUseCase;
import com.grupo29.techflix.useCase.FindVideoUseCase;
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
}
