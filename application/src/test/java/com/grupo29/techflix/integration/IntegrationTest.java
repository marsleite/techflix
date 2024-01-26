package com.grupo29.techflix.integration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWireMock(port = 0)
@ActiveProfiles("test")
public class IntegrationTest {
    @Autowired
    public WebTestClient webTestClient;
}
