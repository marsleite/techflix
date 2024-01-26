package com.grupo29.techflix.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.test.web.reactive.server.WebTestClient;

@Configuration
public class WebClientBean {

    @Bean
    public WebClient webClient() {
        return WebClient.create();
    }

    @Bean
    @Profile("test")
    public WebTestClient webTestClient(WebClient webClient) {
        return WebTestClient.bindToServer()
                .baseUrl("http://localhost:8080")
                .build();
    }
}
