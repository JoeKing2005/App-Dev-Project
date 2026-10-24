package org.example.project.service;

import org.example.project.entities.Joke;

import java.util.List;

public interface JokeService {
    List<Joke> findAll();
    Joke findById(int id);
    void deleteById(int id);
    Joke updateRating(int id, int rating);
}
