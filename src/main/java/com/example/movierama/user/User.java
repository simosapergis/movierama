package com.example.movierama.user;

import com.example.movierama.movie.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity(name = "m_user")
@Table
@Getter @Setter
public class User {
    @Id
    @SequenceGenerator(name = "m_user_sequence", sequenceName = "m_user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_user_sequence")
    private Long id;
    private String name;
    private String password;

    public User() {
    }

    public User(String name) {
        this.name = name;
    }
}
