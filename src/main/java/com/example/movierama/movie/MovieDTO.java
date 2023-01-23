package com.example.movierama.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieDTO {
    @JsonProperty("movieId")
    Long id;
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("posterName")
    String name;
    @JsonProperty("posterLastname")
    String lastName;
    @JsonProperty("posterId")
    Long userId;
    @JsonProperty("postedOn")
    LocalDate publicationDate;
    @JsonProperty("likes")
    int likes;
    @JsonProperty("hates")
    int hates;
    @JsonProperty("liked")
    boolean liked;
    @JsonProperty("hated")
    boolean hated;
    @JsonProperty("owned")
    boolean owned;
}
