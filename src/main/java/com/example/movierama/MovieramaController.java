package com.example.movierama;

import com.example.movierama.constants.AppConstants;
import com.example.movierama.movie.MovieDTO;
import com.example.movierama.movie.MovieResponse;
import com.example.movierama.movie.MovieService;
import com.example.movierama.user.User;
import com.example.movierama.user.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.naming.AuthenticationException;

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
    public ModelAndView index(
                              @RequestParam(value = "postedBy", required = false) String postedBy,
                              @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                              @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo) {

        StringBuilder uri = new StringBuilder("redirect:/movies?sortBy=").append(sortBy);

        if (postedBy != null)
            uri.append("&postedBy=").append(postedBy);

        if (pageNo != null)
            uri.append("&pageNo=").append(pageNo > 0 ? pageNo : 1);

        return new ModelAndView(uri.toString());
    }

    @GetMapping("/movies")
    public String viewHomePage(Model model, @RequestParam(value = "postedBy", required = false) User postedBy,
                               @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
                               @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) Integer pageNo) {

        try {
            final MovieResponse movies = movieService.getMovies(postedBy, sortBy, pageNo);

            model.addAttribute("moviesResponse", movies);

            if (!(AppConstants.DEFAULT_SORT_BY).equals(sortBy))
                model.addAttribute("lastSortBy", sortBy);

        } catch (AuthenticationException ex) {
            log.info("Exception caught : {}", ex.getMessage());
        }

        return "index";

    }

    @GetMapping("/register")
    public String showSignUpForm(Model model) {
        model.addAttribute("user", new User());

        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(@Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "signup_form";

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

    @GetMapping("/profile")
    public String getLogoutPage(@RequestParam(value = "userId") User user, Model model) {
        model.addAttribute("userId", user.getId());
        model.addAttribute("name", user.getName());
        model.addAttribute("lastName", user.getLastName());

        return "profile";
    }
}
