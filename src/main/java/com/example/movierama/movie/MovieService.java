package com.example.movierama.movie;

import com.example.movierama.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getMovies(User user, String sortBy){
        Sort sort = Sort.by(sortBy).descending();

        if (user == null)
            return movieRepository.findAll(sort);
        else
            return movieRepository.findAllByUserOrderByPublicationDateDesc(user);

    }

}
