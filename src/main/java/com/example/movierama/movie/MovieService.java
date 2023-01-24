package com.example.movierama.movie;

import com.example.movierama.movie_opinion.MovieOpinion;
import com.example.movierama.movie_opinion.MovieOpinionRepository;
import com.example.movierama.user.CustomUserDetails;
import com.example.movierama.user.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MovieService {

    private final ModelMapper modelMapper = new ModelMapper();
    private final MovieRepository movieRepository;
    private final MovieOpinionRepository movieOpinionRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository, MovieOpinionRepository movieOpinionRepository) {
        this.movieRepository = movieRepository;
        this.movieOpinionRepository = movieOpinionRepository;
    }

    @PostConstruct
    void configMapper() {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public List<MovieDTO> getMovies(User user, String sortBy) throws AuthenticationException{
        final Sort sort = Sort.by(sortBy).descending();

        List<Movie> movies;

        if (user == null)
            movies = movieRepository.findAll(sort);
        else
            movies = movieRepository.findAllByUserOrderByPublicationDateDesc(user);

        if (!CustomUserDetails.isUserAuthenticated()) {
            log.info("Returning list for authenticated NON user");
            return movies.stream()
                    .map(elem -> modelMapper.map(elem, MovieDTO.class))
                    .collect(Collectors.toList());
        }

        return moviesListForAuthenticated(movies);
    }

    public MovieDTO addMovie(MovieDTO movieDTO) {
        final Movie movie = modelMapper.map(movieDTO, Movie.class);
        movie.setPublicationDate(LocalDateTime.now());
        movieRepository.save(movie);

        return modelMapper.map(movie, MovieDTO.class);
    }

    private List<MovieDTO> moviesListForAuthenticated(List<Movie> allMovies) throws AuthenticationException{
        log.info("Returning list for authenticated user");

        final User authenticatedUser = CustomUserDetails.getAuthenticatedUser();

        if (authenticatedUser == null)
            throw new AuthenticationException("There is no authenticated user.");

        final Optional<List<MovieOpinion>> movieOpinionsOpt = movieOpinionRepository.findAllByUser(authenticatedUser);


        if (movieOpinionsOpt.isPresent()) {
            final List<MovieOpinion> movieOpinionsList = movieOpinionsOpt.get();
            final Map<Long, MovieOpinion> moviesUserReacted = movieOpinionsList.stream()
                    .collect(Collectors.toMap(m -> m.getMovie().getId(), Function.identity()));

            return allMovies.stream()
                    .map(movie -> {
                        final MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);
                        final MovieOpinion userOpinion = moviesUserReacted.get(movie.getId());

                        movieDTO.owned = movie.getUser().getId().equals(authenticatedUser.getId());

                        if (userOpinion!= null) {
                            movieDTO.liked = userOpinion.getLiked();
                            movieDTO.hated = userOpinion.getHated();
                        }

                        return movieDTO;

                    }).toList();

        }

        return  null;
    }
}
