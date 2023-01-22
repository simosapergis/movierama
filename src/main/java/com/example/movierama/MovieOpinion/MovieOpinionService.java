package com.example.movierama.MovieOpinion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MovieOpinionService {
    private final MovieOpinionRepository movieOpinionRepository;

    @Autowired
    public MovieOpinionService(MovieOpinionRepository movieOpinionRepository) {
        this.movieOpinionRepository = movieOpinionRepository;
    }

    List<MovieOpinion> getOpinions(){
        return movieOpinionRepository.findAll();
    }
}
