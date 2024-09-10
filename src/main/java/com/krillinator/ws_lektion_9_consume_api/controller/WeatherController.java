package com.krillinator.ws_lektion_9_consume_api.controller;

import com.krillinator.ws_lektion_9_consume_api.config.WeatherWebClientConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    private final WebClient weatherWebClientConfig;

    public WeatherController(WebClient.Builder webClient) {
        this.weatherWebClientConfig = webClient
                .baseUrl("https://api.open-meteo.com/v1/forecast")
                .build();
    }

    @GetMapping
    public Mono<String> fetchWeatherApi() {

        return weatherWebClientConfig.get()
                .uri(weather -> weather
                        .queryParam("")
                        .build()
                )
                .retrieve()
                .bodyToMono(String.class);
    }

}
