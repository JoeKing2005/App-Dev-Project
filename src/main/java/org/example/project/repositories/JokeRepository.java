package org.example.project.repositories;

import org.example.project.entities.Joke;

import java.util.List;
import java.util.Optional;

public interface JokeRepository {
    List<Joke> findAll();
    Optional<Joke> findById(int id);
    List<Joke> findByCategory(String category);
    boolean deleteById(int id);
    boolean updatePunchline(int id, String punchline);
    Joke save(Joke joke);
}
