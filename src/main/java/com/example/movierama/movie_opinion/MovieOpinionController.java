package com.example.movierama.movie_opinion;

import com.example.movierama.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/movie-opinions")
public class MovieOpinionController {

    MovieOpinionService movieOpinionService;

    @Autowired
    public MovieOpinionController(MovieOpinionService movieOpinionService) {
        this.movieOpinionService = movieOpinionService;
    }

    @GetMapping
    ResponseEntity<List<MovieOpinion>> getOpinions() {
        final List<MovieOpinion> movieOpinions = movieOpinionService.getOpinions();

        return new ResponseEntity<>(movieOpinions, HttpStatus.OK);
    }

    @RequestMapping(path = "/{movieId}/like", method = RequestMethod.POST, produces = "application/json")
    MovieOpinion likeMovie(@PathVariable("movieId") Movie movie) {
        return movieOpinionService.submitLike(movie);
    }

    @RequestMapping(path = "/{movieId}/hate", method = RequestMethod.POST, produces = "application/json")
    MovieOpinion hateMovie(@PathVariable("movieId") Movie movie) {
        return movieOpinionService.submitHate(movie);
    }
}
