package com.example.movierama.movie;

import com.example.movierama.dto.MovieDTO;
import com.example.movierama.user.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<MovieDTO> getMovies(User user, String sortBy) {
        final Sort sort = Sort.by(sortBy).descending();
        final ModelMapper modelMapper = new ModelMapper();
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

}
