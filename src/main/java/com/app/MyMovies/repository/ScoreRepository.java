package com.app.MyMovies.repository;

import com.app.MyMovies.entities.Movie;
import com.app.MyMovies.entities.Score;
import com.app.MyMovies.interfaces.IScoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public class ScoreRepository {
    
    @Autowired
    IScoreRepository repository;
    
    public Iterable<Score> getAll(){
        return repository.findAll();
    }

    public List<Score> getByMovie(Movie movie){
        return repository.getScoresByMovie(movie);
    }
    
    public Optional<Score> findById(String id){
        Optional<Score> response= repository.findById(id);
        return response;
    }
    
    public Boolean existsById(String id){
        return repository.existsById(id);
    }
    
    public void deleteById(String id){
        repository.deleteById(id);
    }
    
    public Score save(Score score){
        return repository.save(score);
    }
    
}
