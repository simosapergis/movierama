package com.example.movierama;

import com.example.movierama.constants.AppConstants;
import com.example.movierama.movie.Movie;
import com.example.movierama.movie.MovieRepository;
import com.example.movierama.user.User;
import com.example.movierama.user.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

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

    private static User user;

    private static Movie testMovie;

    @BeforeAll
    public static void init() {
        user = new User();
        user.setEmail("testForMovieCreation@test.com");
        user.setPassword("asdf1234");
        user.setName("Movie");
        user.setLastName("Testing");


    }

    @Test
    public void testAddMovie() {

        final User savedUser = userRepository.save(user);
        final User existingUser = testEntityManager.find(User.class, savedUser.getId());
        testMovie = new Movie("Test title", LocalDateTime.now(), existingUser, "Test desciption");
        final Movie savedMovie = movieRepository.save(testMovie);
        final Movie existingMovie = testEntityManager.find(Movie.class, savedMovie.getId());

        assertThat(existingMovie.getId()).isEqualTo(testMovie.getId());
    }

    @Test
    public void testGetMovies() {
        final User savedUser = userRepository.save(user);

        testMovie = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");

        movieRepository.save(testMovie);

        final List<Movie> testMoviesList = movieRepository.findAll();

        assertThat(testMoviesList.size()).isGreaterThan(0);
    }


    @Test
    public void testGetMoviesByUser() {
        final User savedUser = userRepository.save(user);
        final Sort sort = Sort.by(AppConstants.DEFAULT_SORT_BY).descending();
        final Pageable pageable = PageRequest.of(0, 10, sort);

        testMovie = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");

        movieRepository.save(testMovie);

        final Page<Movie> testMoviesList = movieRepository.findAllByUser(user, pageable);

        assertThat(testMoviesList.getContent().size()).isGreaterThan(0);
    }

    @Test
    public void testMoviesResponsePagination() {
        final User savedUser = userRepository.save(user);
        final Sort sort = Sort.by(AppConstants.DEFAULT_SORT_BY).descending();
        final Pageable pageable = PageRequest.of(0, 10, sort);
        final Movie m1 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final Movie m2 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final Movie m3 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final Movie m4 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final Movie m5 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final Movie m6 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final Movie m7 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final Movie m8 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final Movie m9 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final Movie m10 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final Movie m11 = new Movie("Test title", LocalDateTime.now(), savedUser, "Test desciption");
        final List<Movie> moviesList = List.of(m1, m2, m3, m4, m5, m6, m7, m8, m9, m10, m11);

        movieRepository.saveAll(moviesList);

        final Page<Movie> testMoviesList = movieRepository.findAll(pageable);

        assertThat(testMoviesList.getContent()).isNotEqualTo(null);
        assertThat(testMoviesList.getTotalPages()).isEqualTo(2);
        assertThat(testMoviesList.getNumberOfElements()).isEqualTo(10);
        assertThat(testMoviesList.getTotalElements()).isEqualTo(11);
    }
}
