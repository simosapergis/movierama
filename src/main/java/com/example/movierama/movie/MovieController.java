package com.example.movierama.movie;

import com.example.movierama.constants.AppConstants;
import com.example.movierama.dto.MovieDTO;
import com.example.movierama.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/movies")
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getMovies(
            @RequestParam(value = "postedBy", required = false) User postedBy,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy) {

        final List<MovieDTO> moviesList = movieService.getMovies(postedBy, sortBy);

        return new ResponseEntity<>(moviesList, HttpStatus.OK);
    }
}