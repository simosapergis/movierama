package com.example.movierama.movie_opinion;

import com.example.movierama.movie.Movie;
import com.example.movierama.user.CustomUserDetails;
import com.example.movierama.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@Slf4j
public class MovieOpinionService {
    private final MovieOpinionRepository movieOpinionRepository;

    @Autowired
    public MovieOpinionService(MovieOpinionRepository movieOpinionRepository) {
        this.movieOpinionRepository = movieOpinionRepository;
    }

    List<MovieOpinion> getOpinions() {
        return movieOpinionRepository.findAll();
    }

    public MovieOpinion submitLike(Movie movie) {
        final User authenticatedUser = CustomUserDetails.getAuthenticatedUser();
        final Optional<MovieOpinion> optionalOpinion = movieOpinionRepository.findByUserAndMovie(authenticatedUser, movie);

        if (optionalOpinion.isPresent()) {
            log.info("Opinion exists for user " + authenticatedUser.getName() + " and movie " + movie.getTitle());

            final MovieOpinion movieOpinion = optionalOpinion.get();
            //Toggle in case the movie is already liked by the user
            movieOpinion.setLiked(!movieOpinion.getLiked());
            movieOpinion.setHated(false);

            return movieOpinionRepository.save(movieOpinion);
        } else {
            log.info("Opinion does not exist for user " + authenticatedUser.getName() + " and movie " + movie.getTitle());

            MovieOpinion newMovieOpinion = new MovieOpinion(movie, authenticatedUser, true, false);

            return movieOpinionRepository.save(newMovieOpinion);
        }
    }

    public MovieOpinion submitHate(Movie movie) {
        final User authenticatedUser = CustomUserDetails.getAuthenticatedUser();
        final Optional<MovieOpinion> optionalOpinion = movieOpinionRepository.findByUserAndMovie(authenticatedUser, movie);

        if (optionalOpinion.isPresent()) {
            log.info("Opinion exists for user " + authenticatedUser.getName() + " and movie " + movie.getTitle());

            final MovieOpinion movieOpinion = optionalOpinion.get();
            //Toggle in case the movie is already liked by the user
            movieOpinion.setHated(!movieOpinion.getHated());
            movieOpinion.setLiked(false);

            return movieOpinionRepository.save(movieOpinion);
        } else {
            log.info("Opinion does not exist for user " + authenticatedUser.getName() + " and movie " + movie.getTitle());

            MovieOpinion newMovieOpinion = new MovieOpinion(movie, authenticatedUser, false, true);

            return movieOpinionRepository.save(newMovieOpinion);
        }
    }
}
