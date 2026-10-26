package org.example.project.service;

import org.example.project.dto.JokeResponse;
import org.example.project.entities.Joke;

import java.util.List;

public interface JokeService {
    List<Joke> findAll();
    Joke findById(int id);
    void deleteById(int id);
    Joke updateRating(int id, int rating);
    List<JokeResponse> findAllSetupsAndPunchlines();
    List<Joke> findByCategory(String category);
    List<Joke> findByAge(int age);
    List<Joke> findByMinimumRating(int rating);
    Double averageRating();
    List<Joke> findByLanguage(String language);
    Joke addJoke(Joke joke);
}
