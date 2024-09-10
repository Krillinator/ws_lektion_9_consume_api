package com.krillinator.ws_lektion_9_consume_api.controller;

import com.krillinator.ws_lektion_9_consume_api.config.WeatherWebClientConfig;
import com.krillinator.ws_lektion_9_consume_api.model.WeatherModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/v1/weather")
public class WeatherController {

    // TODO - More clearly define the usability of Bean
    private final WebClient weatherWebClientConfig;

    public WeatherController(WebClient.Builder webClient) {
        this.weatherWebClientConfig = webClient
                .baseUrl("https://api.open-meteo.com/v1/forecast")
                .build();
    }

    @GetMapping
    public Mono<WeatherModel> fetchWeatherApi() {

        return weatherWebClientConfig.get()
                .uri(weather -> weather
                        .queryParam("latitude", "59.33")
                        .queryParam("longitude", "18.07")
                        .queryParam("hourly", "temperature_2m,rain")
                        .build()
                )
                .retrieve()
                .bodyToMono(WeatherModel.class);
    }

}
