package org.example.project.service;

import lombok.AllArgsConstructor;
import org.example.project.entities.Joke;
import org.example.project.repositories.JokeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class JokeServiceImpl implements JokeService{

    private JokeRepository jokeRepository;


    public Boolean check_setup_and_punchline() {
        return null;
    }

    public Boolean checkCategory() {
        return null;
    }

    public Boolean checkJokeRating() {
        return null;
    }

    public Boolean minAge() {
        return null;
    }

    @Override
    public List<Joke> findAll() {
        return jokeRepository.findAll();
    }
}
