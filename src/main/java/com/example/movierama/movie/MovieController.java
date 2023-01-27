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
    public ResponseEntity<MovieResponse> getMovies(
            @RequestParam(value = "postedBy", required = false) User postedBy,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo) {

        try{
            final MovieResponse movieResponse = movieService.getMovies(postedBy, sortBy, pageNo);

            return new ResponseEntity<>(movieResponse, HttpStatus.OK);
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