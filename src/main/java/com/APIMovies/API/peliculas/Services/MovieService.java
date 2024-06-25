package com.APIMovies.API.peliculas.Services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class MovieService {

    private  String apiKey;
    private final String API_URL = "https://api.themoviedb.org/3/";
    private final WebClient webClient;

    public MovieService(WebClient.Builder webClientBuilder, @Value("${tmdb.api.key}") String apiKey){
        this.webClient = webClientBuilder.baseUrl(API_URL).build();
        this.apiKey = apiKey;
    }

    @Cacheable("movies")
    public Mono<String> searchMovies(String name){
        return this.webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("search/movie")
                        .queryParam("api_key", apiKey)
                        .queryParam("query", name)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
}
