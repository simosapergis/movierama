package com.example.movierama.movie;

import com.example.movierama.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import java.time.LocalDate;

@Table
@Entity
@Data
@NoArgsConstructor
public class Movie {
    @Id
    @SequenceGenerator(name = "movie_sequence", sequenceName = "movie_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_sequence")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "poster_id", nullable = false)
    private User user;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(length = 1500, nullable = false)
    private String description;

    private LocalDate publicationDate;

    @Formula("(SELECT COUNT(1) FROM movie_opinion mo WHERE mo.movie_id = id AND mo.liked = true)")
    private int likes;

    @Formula("(SELECT COUNT(1) FROM movie_opinion mo WHERE mo.movie_id = id AND mo.hated = true)")
    private int hates;


    public Movie(String title, LocalDate publicationDate, User user, String description) {
        this.title = title;
        this.publicationDate = publicationDate;
        this.user = user;
        this.description = description;
    }
}
