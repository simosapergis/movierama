package com.sapergis.movierama.movie_opinion;

import com.sapergis.movierama.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/movie-opinions")
public class MovieOpinionController {

    MovieOpinionService movieOpinionService;

    @Autowired
    public MovieOpinionController(MovieOpinionService movieOpinionService) {
        this.movieOpinionService = movieOpinionService;
    }

    @RequestMapping(path = "/{movieId}/like", method = RequestMethod.PUT, produces = "text/html")
    String likeMovie(@PathVariable("movieId") Movie movie) {
        return movieOpinionService.submitLike(movie);
    }

    @RequestMapping(path = "/{movieId}/hate", method = RequestMethod.PUT, produces = "text/html")
    String hateMovie(@PathVariable("movieId") Movie movie) {
        return movieOpinionService.submitHate(movie);
    }
}
