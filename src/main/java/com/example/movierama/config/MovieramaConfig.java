package com.example.movierama.config;

import com.example.movierama.movie_opinion.MovieOpinion;
import com.example.movierama.movie_opinion.MovieOpinionRepository;
import com.example.movierama.movie.Movie;
import com.example.movierama.movie.MovieRepository;
import com.example.movierama.user.User;
import com.example.movierama.user.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class MovieramaConfig {

    @Bean
    CommandLineRunner commandLineRunner(MovieRepository movieRepository, UserRepository userRepository, MovieOpinionRepository movieOpinionRepository) {
        return args -> {
            final User user1 = new User("user1@test.com", "user1234", "Frank", "Miller");
            final User user2 = new User("user2@test.com", "user1234", "Jason", "Momoa");
            final User user3 = new User("user3@test.com", "user1234", "Mark", "Stores");
            final User user4 = new User("user4@test.com", "user1234", "Bob", "Michael");
            final User user5 = new User("user5@test.com", "user1234", "Simos", "Apergis");
            final User user6 = new User("user6@test.com", "user1234", "Jim", "MIller");
            final User user7 = new User("user7@test.com", "user1234", "Nick", "Diaz");
            final User user8 = new User("user8@test.com", "user1234", "Steve", "Papa");
            final User user9 = new User("user9@test.com", "user1234", "John", "Digweed");
            final User user10 = new User("user10@test.com", "$2a$10$aFZXXmzrebzvdHk0D/Oe8.cgiZrmK0qWvAjhcL8wbLlUZGz8dQ38m", "Nate", "Diaz");

            userRepository.saveAll(List.of(user1, user2, user3, user4, user5, user6, user7, user8, user9, user10));

            final Movie movie1 = new Movie("Top Gun", LocalDateTime.now().minusDays(10), user10, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie2 = new Movie("The Shooter", LocalDateTime.now().minusDays(20), user1, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie3 = new Movie("The Wall", LocalDateTime.now().minusDays(24), user7, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie4 = new Movie("American Gangster", LocalDateTime.now().minusDays(0), user2, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie5 = new Movie("Hello World", LocalDateTime.now().minusDays(1), user10, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie6 = new Movie("Earthquake", LocalDateTime.now().minusDays(3), user6, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie7 = new Movie("Ring The Bell", LocalDateTime.now().minusDays(4), user3, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie8 = new Movie("Revolution", LocalDateTime.now().minusDays(1), user4, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie9 = new Movie("Don't Be Afraid", LocalDateTime.now().minusDays(11), user9, "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");
            final Movie movie10 = new Movie("Charisma", LocalDateTime.now().minusDays(0), user9,"Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum");

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
