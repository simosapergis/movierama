package com.sapergis.movierama;

import com.sapergis.movierama.movie.Movie;
import com.sapergis.movierama.movie.MovieRepository;
import com.sapergis.movierama.movie_opinion.MovieOpinion;
import com.sapergis.movierama.movie_opinion.MovieOpinionRepository;
import com.sapergis.movierama.user.User;
import com.sapergis.movierama.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;


@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MovieOpinionRepositoryTests {
    @Autowired
    private MovieOpinionRepository movieOpinionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    public void getMovieOpinionsByUser() {
        final User user = new User();
        user.setEmail("testForMovieCreation@test.com");
        user.setPassword("asdf1234");
        user.setName("Movie");
        user.setLastName("Testing");

        final User savedUser = userRepository.save(user);
        final User existingUser = testEntityManager.find(User.class, savedUser.getId());

        final Movie testMovie = new Movie("Test title", LocalDateTime.now(), existingUser, "Test desciption");
        final Movie savedMovie = movieRepository.save(testMovie);
        final Movie existingMovie = testEntityManager.find(Movie.class, savedMovie.getId());

        final MovieOpinion movieOpinion = new MovieOpinion(existingMovie, existingUser, true, false);
        final MovieOpinion savedMovieOpinion = movieOpinionRepository.save(movieOpinion);
        final MovieOpinion existingMovieOpinion = testEntityManager.find(MovieOpinion.class, savedMovieOpinion.getId());

        assertThat(existingMovieOpinion.getId()).isEqualTo(movieOpinion.getId());
    }
}
