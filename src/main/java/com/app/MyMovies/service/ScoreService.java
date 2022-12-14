package com.app.MyMovies.service;

import com.app.MyMovies.dto.ResponseDto;
import com.app.MyMovies.entities.Movie;
import com.app.MyMovies.entities.Score;
import com.app.MyMovies.repository.ScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ScoreService {
    private final String SCORE_REGISTERED="el puntaje ya estaba registrado mas de una vez";
    private final String SCORE_SUCCESS="el puntaje se registró correctamente";
    @Autowired
    ScoreRepository repository;

    public Iterable<Score> get() {
        Iterable<Score> response = repository.getAll();
        return response;
    }

    public ResponseDto create(Score request) {
        ResponseDto response = new ResponseDto();
        List<Score> scores = repository.getByMovie(request.getMovie());
        if(request.getScore().intValue()<0 || request.getScore().intValue()>5){
            response.status=false;
            response.message="la calificación no está dentro del rango esperados";
        }else{
            if(scores.size()>0){
                response.status=false;
                response.message=SCORE_REGISTERED;
            }
            else{
                repository.save(request);
                response.status=true;
                response.message=SCORE_SUCCESS;
                response.id= request.getId();
            }
        }
        return response;
    }

    public Score update(Score score) {
        Score scoreToUpdate = new Score();

        Optional<Score> currentScore = repository.findById(score.getId());
        if (!currentScore.isEmpty()) {
            scoreToUpdate = score;
            scoreToUpdate=repository.save(scoreToUpdate);
        }
        return scoreToUpdate;
    }

    public Boolean delete(String id) {
        repository.deleteById(id);
        Boolean deleted = true;
        return deleted;
    }
}
