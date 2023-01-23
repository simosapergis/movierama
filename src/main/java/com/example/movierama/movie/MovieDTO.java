package com.example.movierama.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

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
    @JsonProperty("postedWhen")
    String postedWhen;
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

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
        setPostedWhen();
    }

    public void setPostedWhen() {
        final long postedDaysAgo = DAYS.between(this.publicationDate, LocalDate.now());

        switch ((int) postedDaysAgo) {
            case 0 -> this.postedWhen = "Today";
            case 1 -> this.postedWhen = "1 day ago";
            default -> this.postedWhen = postedDaysAgo + " days ago";
        }
    }
}
