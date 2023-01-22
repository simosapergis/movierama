package com.example.movierama.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MovieDTO {
    @JsonProperty("id")
    Long id;
    @JsonProperty("title")
    String title;
    @JsonProperty("description")
    String description;
    @JsonProperty("postedBy")
    String name;
    @JsonProperty("postedOn")
    LocalDate publicationDate;
    @JsonProperty("likes")
    int likes;
    @JsonProperty("hates")
    int hates;
}
