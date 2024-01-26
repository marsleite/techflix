package com.grupo29.techflix.entrypoints;

import com.grupo29.techflix.integration.IntegrationTest;
import org.junit.jupiter.api.Test;

public class VideoHandlerImplTest extends IntegrationTest {

    @Test
    public void shouldCreateVideo() {
        webTestClient.post()
                .uri("/videos")
                .bodyValue("{\n" +
                        "  \"titulo\": \"titulo\",\n" +
                        "  \"descricao\": \"descricao\",\n" +
                        "  \"url\": \"url\",\n" +
                        "  \"categoria\": \"COMEDIA\"\n" +
                        "}")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.titulo").isEqualTo("titulo")
                .jsonPath("$.descricao").isEqualTo("descricao")
                .jsonPath("$.url").isEqualTo("url")
                .jsonPath("$.categoria").isEqualTo("COMEDIA");
    }
}
