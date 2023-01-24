package com.example.movierama;

import com.example.movierama.movie.Movie;
import com.example.movierama.movie.MovieRepository;
import com.example.movierama.user.User;
import com.example.movierama.user.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class MovieRepositoryTests {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testAddMovie() {
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

        assertThat(existingMovie.getId()).isEqualTo(testMovie.getId());
    }

}
