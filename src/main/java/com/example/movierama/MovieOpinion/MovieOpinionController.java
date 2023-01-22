package com.example.movierama.MovieOpinion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/opinions")
public class MovieOpinionController {

    MovieOpinionService movieOpinionService;

    @Autowired
    public MovieOpinionController(MovieOpinionService movieOpinionService) {
        this.movieOpinionService = movieOpinionService;
    }

    @GetMapping
    List<MovieOpinion> getOpinions() {
        return movieOpinionService.getOpinions();
    }
}
