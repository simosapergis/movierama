package com.example.movierama.movie;

import com.example.movierama.constants.AppConstants;
import com.example.movierama.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

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

        try{
            final List<MovieDTO> moviesList = movieService.getMovies(postedBy, sortBy);

            return new ResponseEntity<>(moviesList, HttpStatus.OK);
        } catch (AuthenticationException ex) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

    }

    @PostMapping
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO movieDTO){
        final MovieDTO postedMovieDTO = movieService.addMovie(movieDTO);

        return new ResponseEntity<>(postedMovieDTO, HttpStatus.CREATED);
    }
}