package com.example.movierama.movie;

import com.example.movierama.dto.MovieDTO;
import com.example.movierama.user.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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

    @Lazy
    void configMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<MovieDTO> getMovies(User user, String sortBy) {
        final Sort sort = Sort.by(sortBy).descending();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);

        List<Movie> movies;

        if (user == null)
            movies = movieRepository.findAll(sort);
        else
            movies = movieRepository.findAllByUserOrderByPublicationDateDesc(user);

        return movies.stream()
                .map(elem -> modelMapper.map(elem, MovieDTO.class))
                .collect(Collectors.toList());
    }

    public void postMovie(MovieDTO movieDTO) {
        Movie movie = movieRepository.save(modelMapper.map(movieDTO, Movie.class));
        System.out.println("posted movie is " + movie);
    }

}
