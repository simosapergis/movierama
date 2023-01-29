package com.example.movierama;

import com.example.movierama.movie.MovieService;
import com.example.movierama.movie_opinion.MovieOpinionService;
import com.example.movierama.user.UserService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest
public class MovieOpinionControllerTest {

    @Autowired
    WebApplicationContext context;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    MovieOpinionService movieOpinionService;

    @MockBean
    UserService userService;

    @MockBean
    MovieService movieService;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void testGetOpinions() throws Exception {
        mockMvc.perform(get("/api/v1/movie-opinions"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void testLikeMovieUnauthorized() throws Exception {
        mockMvc.perform(get("/api/v1/movie-opinions/3/like"))
                .andExpect(status().isUnauthorized());
    }
}
