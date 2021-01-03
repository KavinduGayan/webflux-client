package com.example.webfluxclient.controller;

import com.example.webfluxclient.model.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/webflux")
public class WebFluxController {

    private final WebClient webClient;

    public WebFluxController(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8085/datasource").build();;
    }

    @GetMapping("/string")
    public Mono<String> getString () {
        return this.webClient
                .get()
                .uri("/string")
                .retrieve()
                .bodyToMono(String.class);
    }

    @GetMapping("/user")
    public Mono<User> userMono() {
        return this.webClient
                .get()
                .uri("/user")
                .retrieve()
                .bodyToMono(User.class);
    }

    @GetMapping("/user-list")
    public Flux<User> userList() {
        return this.webClient
                .get()
                .uri("/user-list")
                .retrieve()
                .bodyToFlux(User.class);
    }
}
