package com.example.movierama;

import com.example.movierama.constants.AppConstants;
import com.example.movierama.movie.MovieDTO;
import com.example.movierama.movie.MovieService;
import com.example.movierama.user.CustomUserDetails;
import com.example.movierama.user.User;
import com.example.movierama.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class MovieramaController {

    private final UserService userService;
    private final MovieService movieService;

    @Autowired
    public MovieramaController(UserService userService, MovieService movieService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @GetMapping("/")
    public String viewHomePage(Model model) {
        CustomUserDetails.isUserAuthenticated();
        List<MovieDTO> movies = movieService.getMovies(null, AppConstants.DEFAULT_SORT_BY);
        model.addAttribute("movies", movies);
        return "index";
    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = ((CustomUserDetails)((UsernamePasswordAuthenticationToken)auth).getPrincipal()).getUser();
//
//        if (user != null)
//            //code
//
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(User user) {
        userService.processRegistration(user);

        return "registration_success";
    }

    @GetMapping("/new_movie_form")
    public String showNewMovieForm(Model model) {
        model.addAttribute(new MovieDTO());
        return "new_movie_form";
    }

    @PostMapping("/movies")
    public String addNewMovie(MovieDTO movieDTO) {
        movieService.addMovie(movieDTO);
        return "add_movie_success";
    }
}
