package org.example.project.service;

import org.example.project.entities.Joke;

import java.util.List;

public interface JokeService {
    List<Joke> findAll();
}
