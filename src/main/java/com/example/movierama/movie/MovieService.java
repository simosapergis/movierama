package com.example.movierama.movie;

import com.example.movierama.constants.AppConstants;
import com.example.movierama.movie_opinion.MovieOpinion;
import com.example.movierama.movie_opinion.MovieOpinionRepository;
import com.example.movierama.user.CustomUserDetails;
import com.example.movierama.user.User;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.*;
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

    public MovieResponse getMovies(User user, String sortBy, int pageNo) throws AuthenticationException {
        final Sort sort = Sort.by(sortBy).descending();
        final Pageable pageable = PageRequest.of(pageNo, AppConstants.DEFAULT_PAGE_SIZE, sort);

        Page<Movie> paginateMovies;

        if (user == null)
            paginateMovies = movieRepository.findAll(pageable);
        else
            paginateMovies = movieRepository.findAllByUser(user, pageable);

        final List<Movie> movies = paginateMovies.getContent();
        List<MovieDTO> contentList;

        if (!CustomUserDetails.isUserAuthenticated()) {
            log.info("Returning list for authenticated NON user");
            contentList = movies.stream()
                    .map(elem -> modelMapper.map(elem, MovieDTO.class))
                    .toList();
        } else {
            contentList =  moviesListForAuthenticated(movies);
        }

        final MovieResponse movieResponse = new MovieResponse();
        movieResponse.setContent(contentList);
        movieResponse.setPageNo(paginateMovies.getNumber());
        movieResponse.setPageSize(paginateMovies.getSize());
        movieResponse.setTotalElements(paginateMovies.getTotalElements());
        movieResponse.setTotalPages(paginateMovies.getTotalPages());
        movieResponse.setLast(paginateMovies.isLast());

        return movieResponse;
    }

    public MovieDTO addMovie(MovieDTO movieDTO) {
        final Movie movie = modelMapper.map(movieDTO, Movie.class);
        movie.setPublicationDate(LocalDateTime.now());
        movie.setUser(CustomUserDetails.getAuthenticatedUser());
        movieRepository.save(movie);

        return modelMapper.map(movie, MovieDTO.class);
    }

    private List<MovieDTO> moviesListForAuthenticated(List<Movie> allMovies) throws AuthenticationException {
        log.info("Returning list for authenticated user");

        final User authenticatedUser = CustomUserDetails.getAuthenticatedUser();

        //If by any chance the user is non authenticated, throw exception
        if (authenticatedUser == null)
            throw new AuthenticationException("There is no authenticated user.");

        //Fetch Optional object with all movie opinions by the user, if any
        final Optional<List<MovieOpinion>> optOpinionsListForUser = movieOpinionRepository.findAllByUser(authenticatedUser);
        List<MovieOpinion> opinionsListForUser = new ArrayList<>();

        //If movie opinions found, get the list from Optional
        if (optOpinionsListForUser.isPresent()) {
            opinionsListForUser = optOpinionsListForUser.get();
        }

        //Create a HashMap of MovieOpinions having as key the movie Id, for convenience
        final Map<Long, MovieOpinion> moviesUserReacted = opinionsListForUser.stream()
                .collect(Collectors.toMap(m -> m.getMovie().getId(), Function.identity()));

        //create a stream and process all movies in order to create the MovieDTO for the response
        return allMovies.stream()
                .map(movie -> {
                    final MovieDTO movieDTO = modelMapper.map(movie, MovieDTO.class);

                    //Indicating if the user is the owner of the current Movie post
                    movieDTO.owned = movie.getUser().getId().equals(authenticatedUser.getId());

                    //If movie opinions exist for the current user, add information if user liked/hated the movie
                    if (moviesUserReacted.size() > 0) {
                        final MovieOpinion userOpinion = moviesUserReacted.get(movie.getId());

                        if (userOpinion != null) {
                            movieDTO.liked = userOpinion.getLiked();
                            movieDTO.hated = userOpinion.getHated();
                        }
                    }

                    return movieDTO;

                }).toList();
    }

}
