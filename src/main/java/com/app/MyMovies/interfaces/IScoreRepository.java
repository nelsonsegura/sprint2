package com.app.MyMovies.interfaces;

import com.app.MyMovies.entities.Client;
import com.app.MyMovies.entities.Movie;
import com.app.MyMovies.entities.Score;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;


public interface IScoreRepository extends MongoRepository<Score, String> {
    @Query(value= "{movie : ?0}") // SQL Equivalent : SELECT * FROM Movie select * from Movie where name=?
    List<Score> getScoresByMovie(Movie movie);
}
