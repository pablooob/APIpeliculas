package com.APIMovies.API.peliculas.Controllers;

import com.APIMovies.API.peliculas.Services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public Mono<String> getMovies(@RequestParam String name){
        Mono<String> result = movieService.searchMovies(name);
        return result;
    }
}
