package com.sapergis.movierama.movie_opinion;

import com.sapergis.movierama.movie.Movie;
import com.sapergis.movierama.movie.MovieRepository;
import com.sapergis.movierama.user.CustomUserDetails;
import com.sapergis.movierama.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

@Slf4j
public class MovieOpinionService {
    private final MovieOpinionRepository movieOpinionRepository;

    private final MovieRepository movieRepository;

    @Autowired
    public MovieOpinionService(MovieOpinionRepository movieOpinionRepository, MovieRepository movieRepository) {
        this.movieOpinionRepository = movieOpinionRepository;
        this.movieRepository = movieRepository;
    }

    List<MovieOpinion> getOpinions() {
        return movieOpinionRepository.findAll();
    }

    public String submitLike(Movie movie) {
        final User authenticatedUser = CustomUserDetails.getAuthenticatedUser();
        final Optional<MovieOpinion> optionalExistingOpinion = movieOpinionRepository.findByUserAndMovie(authenticatedUser, movie);
        MovieOpinion movieOpinion;

        if (optionalExistingOpinion.isPresent()) {
            log.info("User {} opinion for movie {} already exists", authenticatedUser.getName(), movie.getTitle());

            movieOpinion = optionalExistingOpinion.get();
            //Toggle in case the movie is already liked by the user
            movieOpinion.setLiked(!movieOpinion.getLiked());
            movieOpinion.setHated(false);

        } else {
            log.info("User {} has no existing opinion for movie {}", authenticatedUser.getName(), movie.getTitle());

            movieOpinion = new MovieOpinion(movie, authenticatedUser, true, false);
        }


        final MovieOpinion storedOpinion = movieOpinionRepository.saveAndFlush(movieOpinion);
        final int updatedLikesCount = movieOpinionRepository.countByLikedIsTrueAndMovie(storedOpinion.getMovie());
        final int updatedHatesCount = movieOpinionRepository.countByHatedIsTrueAndMovie(storedOpinion.getMovie());
        final MovieOpinionResponse response = new MovieOpinionResponse(storedOpinion, updatedLikesCount, updatedHatesCount);

        return response.createLikeResponse();
    }

    public String submitHate(Movie movie) {
        final User authenticatedUser = CustomUserDetails.getAuthenticatedUser();
        final Optional<MovieOpinion> optionalExistingOpinion = movieOpinionRepository.findByUserAndMovie(authenticatedUser, movie);
        MovieOpinion movieOpinion;

        if (optionalExistingOpinion.isPresent()) {
            log.info("User {} opinion for movie {} already exists", authenticatedUser.getName(), movie.getTitle());

            movieOpinion = optionalExistingOpinion.get();
            //Toggle in case the movie is already liked by the user
            movieOpinion.setHated(!movieOpinion.getHated());
            movieOpinion.setLiked(false);

        } else {
            log.info("User {} has no existing opinion for movie {}", authenticatedUser.getName(), movie.getTitle());

            movieOpinion = new MovieOpinion(movie, authenticatedUser, false, true);
        }

        final MovieOpinion storedOpinion = movieOpinionRepository.saveAndFlush(movieOpinion);
        final int updatedLikesCount = movieOpinionRepository.countByLikedIsTrueAndMovie(storedOpinion.getMovie());
        final int updatedHatesCount = movieOpinionRepository.countByHatedIsTrueAndMovie(storedOpinion.getMovie());
        final MovieOpinionResponse response = new MovieOpinionResponse(storedOpinion, updatedLikesCount, updatedHatesCount);

        return response.createHateResponse();
    }
}
