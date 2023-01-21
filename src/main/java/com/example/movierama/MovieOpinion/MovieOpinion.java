package com.example.movierama.MovieOpinion;

import com.example.movierama.movie.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table
@Entity
@Getter
@Setter
public class MovieOpinion {
    @Id
    private Long id;

//    private Movie movie;

    private Boolean liked;

    private Boolean hated;
}
