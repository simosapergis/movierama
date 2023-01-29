package com.example.movierama;

import com.example.movierama.movie.MovieController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class MovieramaApplicationTests {

    @Autowired
    MovieController movieController;

    @Test
    void contextLoads() {
        assertThat(movieController).isNotNull();
    }

}
