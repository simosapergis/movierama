package com.example.movierama.movie_opinion;

import com.example.movierama.movie.Movie;
import com.example.movierama.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieOpinionRepository extends JpaRepository<MovieOpinion, Long> {
    Optional<MovieOpinion> findByUserAndMovie(User user, Movie movie);
}
