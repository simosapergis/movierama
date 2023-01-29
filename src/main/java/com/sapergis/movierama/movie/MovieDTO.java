package com.sapergis.movierama.movie;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;


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
    LocalDateTime publicationDate;
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

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
        setPostedWhen();
    }

    public void setPostedWhen() {
        final long postedDaysAgo = ChronoUnit.DAYS.between(this.publicationDate, LocalDateTime.now());
        long minutesAgo = -1;
        long hoursAgo = 0;

        if (postedDaysAgo == 0)
            hoursAgo = ChronoUnit.HOURS.between(this.publicationDate, LocalDateTime.now());

        if (hoursAgo < 1)
            minutesAgo = ChronoUnit.MINUTES.between(this.publicationDate, LocalDateTime.now());

        if (postedDaysAgo == 1)
            this.postedWhen = "1 day ago";
        else if (postedDaysAgo > 0)
            this.postedWhen = postedDaysAgo + " days ago";
        else if (minutesAgo > 0)
            this.postedWhen = minutesAgo + " minutes ago";
        else if (hoursAgo > 0)
            this.postedWhen = hoursAgo + " hours ago";
        else
            this.postedWhen = "A few seconds ago";
    }
}
