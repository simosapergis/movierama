package com.example.movierama.movie;

import com.example.movierama.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies(){
        return movieRepository.findAll();
    }

    public List<Movie> getMoviesByPosterId(User user) {
        return movieRepository.findAllByUserOrderByPublicationDateDesc(user);
    }
}
