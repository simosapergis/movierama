package com.sapergis.movierama.movie_opinion;

import com.sapergis.movierama.movie.Movie;
import com.sapergis.movierama.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class MovieOpinion {
    @Id
    @SequenceGenerator(name = "movie_opinion_sequence", sequenceName = "movie_opinion_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_opinion_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Movie movie;

    @OneToOne
    private User user;

    private Boolean liked;

    private Boolean hated;

    public MovieOpinion(Movie movie, User user, Boolean liked, Boolean hated) {
        this.movie = movie;
        this.user = user;
        this.liked = liked;
        this.hated = hated;
    }
}
