package com.example.movierama.movie_opinion;

import com.example.movierama.movie.Movie;

public class MovieOpinionResponse {


    private static final String OPEN_HTML_ELEMENT = """
            <span id="op-mutable" style="display: flex; position:relative;">""";

    private static final String CLOSE_HTML_ELEMENT = """
            </span>""";

    private final MovieOpinion movieOpinion;
    private final Movie movie;

    private final int updatedLikesCount;

    private final int updatedHatesCount;

    public MovieOpinionResponse(MovieOpinion movieOpinion, int updatedLikesCount, int updatedHatesCount) {
        this.movieOpinion = movieOpinion;
        this.movie = movieOpinion.getMovie();
        this.updatedLikesCount = updatedLikesCount;
        this.updatedHatesCount = updatedHatesCount;
    }

    public String createLikeResponse() {
        final StringBuilder sb = new StringBuilder(OPEN_HTML_ELEMENT);

        if (movieOpinion.getLiked()) {
            sb.append(selectableHateElementOnly());
            sb.append(likedMovieText());
        } else{
            sb.append(selectableAllOpinionElements());
        }


        return  sb.append(CLOSE_HTML_ELEMENT).toString();
    }


    public String createHateResponse() {
        final StringBuilder sb = new StringBuilder(OPEN_HTML_ELEMENT);

        if (movieOpinion.getHated()) {
            sb.append(selectableLikeElementOnly());
            sb.append(hatedMovieText());
        } else{
            sb.append(selectableAllOpinionElements());
        }


        return  sb.append(CLOSE_HTML_ELEMENT).toString();
    }

    private String selectableAllOpinionElements() {
        final String opinionsArea = """
                    <p>
                    <div style="display: flex;">
                        <a hx-trigger="click" hx-post="/api/v1/movie-opinions/%s/like"
                           hx-target="closest span"
                           hx-swap="outerHTML" style="color: #007bff; cursor: pointer;"
                           >Likes %s |
                        </a>
                        <a hx-trigger="click" hx-post="/api/v1/movie-opinions/%s/hate"
                           hx-target="closest span"
                           hx-swap="outerHTML" style="color: #007bff; cursor: pointer;"
                           >Hates %s
                        </a>
                    </div>
                """;

        return String.format(opinionsArea, movie.getId(), updatedLikesCount, movie.getId(), updatedHatesCount);
    }

    private String selectableLikeElementOnly() {
        final String opinionsArea = """
                        <p>
                        <div style="display: flex;">
                            <a hx-trigger="click" hx-post="/api/v1/movie-opinions/%s/like"
                               hx-target="closest span"
                               hx-swap="outerHTML" style="color: #007bff; cursor: pointer;"
                               >Likes %s |
                            </a>
                            <div style="margin-left: 5px;">Hates %s
                            </div>
                        </div>
                    """;

        return String.format(opinionsArea, movie.getId(), updatedLikesCount, updatedHatesCount);
    }

    private String selectableHateElementOnly() {
        final String opinionsArea = """
                        <p>
                        <div style="display: flex;">
                            <div>Likes %s |
                            </div>
                            <a hx-trigger="click" hx-post="/api/v1/movie-opinions/%s/hate"
                               hx-target="closest span"
                               hx-swap="outerHTML" style="color: #007bff; cursor: pointer;"
                               > Hates %s
                            </a>
                        </div>
                    """;

        return String.format(opinionsArea, updatedLikesCount, movie.getId(), updatedHatesCount);
    }

    private String likedMovieText() {
        String likedMovieText = """
            <div style="position: absolute; right: 0;">
                <div style="display: flex">
                    <div>You liked this movie | </div>
                    <a style="margin-left: 5px; color: #007bff; cursor: pointer;"
                        hx-trigger="click"
                        hx-target="closest span"
                        hx-swap="outerHTML"
                        hx-post="/api/v1/movie-opinions/%s/like">Unlike</a>
                </div>
            </div>
         """;

        return String.format(likedMovieText, movie.getId());
    }

    private String hatedMovieText() {
        String likedMovieText = """
            <div style="position: absolute; right: 0;">
                <div style="display: flex">
                    <div>You hated this movie | </div>
                    <a style="margin-left: 5px; color: #007bff; cursor: pointer;"
                        hx-trigger="click"
                        hx-target="closest span"
                        hx-swap="outerHTML"
                        hx-post="/api/v1/movie-opinions/%s/hate">Unhate</a>
                </div>
            </div>
         """;

        return String.format(likedMovieText, movie.getId());
    }

}
