package com.example.movierama.movie;

import com.example.movierama.MovieOpinion.MovieOpinion;
import com.example.movierama.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Table
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Movie {
    @Id
    @SequenceGenerator(name = "movie_sequence", sequenceName = "movie_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "poster_id")
    private User user;

    private String title;

    @Column(length = 1500)
    private String description;

    private LocalDate publicationDate;

//    @ManyToMany
//    private List<MovieOpinion> opinions;

    public Movie(String title, LocalDate publicationDate, User user, String description) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.user = user;
        this.description = description;
    }
}
