package org.example.project.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.project.entities.Joke;
import org.example.project.repositories.JokeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
public class JokeServiceImpl implements JokeService{

    private JokeRepository jokeRepository;

    @Override
    public List<Joke> findAll() {
        return jokeRepository.findAll();
    }

    @Override
    public Joke findById(int id) {
        return jokeRepository.findById(id)
                .orElseThrow(() -> new JokeNotFoundException("Joke with ID " + id + "was not found"));
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        Joke joke = findById(id);
        jokeRepository.delete(joke);
    }

    @Override
    @Transactional
    public Joke updateRating(int id, int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException(
                    "Rating must be between 1 and 5"
            );
        }

        Joke joke = findById(id);
        joke.setRating(rating);
        return joke;
    }
}
