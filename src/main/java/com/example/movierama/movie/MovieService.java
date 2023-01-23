package com.example.movierama.movie;

import com.example.movierama.user.User;
import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @PostConstruct
    void configMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<MovieDTO> getMovies(User user, String sortBy) {
        final Sort sort = Sort.by(sortBy).descending();

        List<Movie> movies;

        if (user == null)
            movies = movieRepository.findAll(sort);
        else
            movies = movieRepository.findAllByUserOrderByPublicationDateDesc(user);

        return movies.stream()
                .map(elem -> modelMapper.map(elem, MovieDTO.class))
                .collect(Collectors.toList());
    }

    public MovieDTO addMovie(MovieDTO movieDTO) {
        final Movie movie = modelMapper.map(movieDTO, Movie.class);
        movie.setPublicationDate(LocalDateTime.now());
        movieRepository.save(movie);

        return modelMapper.map(movie, MovieDTO.class);
    }

}
