package com.example.movierama.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "m_user")
@Table
@NoArgsConstructor
@Data
public class User {
    @Id
    @SequenceGenerator(name = "m_user_sequence", sequenceName = "m_user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "m_user_sequence")
    private Long id;
    private String name;
    private String password;

    public User(String name) {
        this.name = name;
    }
}
