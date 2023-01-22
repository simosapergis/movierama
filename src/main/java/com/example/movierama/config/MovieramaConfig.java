package com.example.movierama.config;

import com.example.movierama.MovieOpinion.MovieOpinion;
import com.example.movierama.MovieOpinion.MovieOpinionRepository;
import com.example.movierama.movie.Movie;
import com.example.movierama.movie.MovieRepository;
import com.example.movierama.user.User;
import com.example.movierama.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class MovieramaConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository, UserRepository userRepository, MovieOpinionRepository movieOpinionRepository) {
        return args -> {
            final User user1 = new User("user1");
            final User user2 = new User("user2");
            final User user3 = new User("user3");
            final User user4 = new User("user4");
            final User user5 = new User("user5");
            final User user6 = new User("user6");
            final User user7 = new User("user7");
            final User user8 = new User("user8");
            final User user9 = new User("user9");
            final User user10 = new User("user10");

            userRepository.saveAll(List.of(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));

            final Movie movie1 = new Movie("Top Gun", LocalDate.now().minusDays(10), user10, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie2 = new Movie("The Shooter", LocalDate.now().minusDays(20), user1, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie3 = new Movie("The Wall", LocalDate.now().minusDays(24), user7, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie4 = new Movie("American Gangster", LocalDate.now().minusDays(0), user2, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie5 = new Movie("Hello World", LocalDate.now().minusDays(1), user10, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie6 = new Movie("Earthquake", LocalDate.now().minusDays(3), user6, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie7 = new Movie("Ring The Bell", LocalDate.now().minusDays(4), user3, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie8 = new Movie("Revolution", LocalDate.now().minusDays(1), user4, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie9 = new Movie("Don't Be Afraid", LocalDate.now().minusDays(11), user9, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie10 = new Movie("Charisma", LocalDate.now().minusDays(0), user9,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");

            movieRepository.saveAll(List.of(movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10));

            final MovieOpinion movieOpinion1 = new MovieOpinion(movie2, user1,true, false);
            final MovieOpinion movieOpinion2 = new MovieOpinion(movie1, user4,true, false);
            final MovieOpinion movieOpinion3 = new MovieOpinion(movie2, user1,false, true);
            final MovieOpinion movieOpinion4 = new MovieOpinion(movie4, user3, true, false);
            final MovieOpinion movieOpinion5 = new MovieOpinion(movie2, user1,false, true);
            final MovieOpinion movieOpinion6 = new MovieOpinion(movie2, user2, false, true);
            final MovieOpinion movieOpinion7 = new MovieOpinion(movie2, user3, true, false);

            movieOpinionRepository.saveAll(List.of(movieOpinion1, movieOpinion2,  movieOpinion3, movieOpinion4, movieOpinion5,movieOpinion6, movieOpinion7));
        };
    }
}
