package org.example.project.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.example.project.dto.JokeResponse;
import org.example.project.entities.Joke;
import org.example.project.repositories.JokeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

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

    @Override
    public List<JokeResponse> findAllSetupsAndPunchlines() {
        return jokeRepository.findALlProjectedBy();
    }

    @Override
    public List<Joke> findByCategory(String category) {
        return jokeRepository.findByCategory(category);
    }

    @Override
    public List<Joke> findByAge(int age) {
        return jokeRepository.findByAgeAppropriatenessLessThanEqual(age);
    }

    @Override
    public List<Joke> findByMinimumRating(int rating) {
        return jokeRepository.findByRatingGreaterThanEqual(rating);
    }

    @Override
    public Double averageRating() {
        return jokeRepository.averageRating();
    }

    @Override
    public List<Joke> findByLanguage(String language) {
        if (language == null || language.isBlank()) {
            throw new IllegalArgumentException("Language can't be blank");
        }

        return jokeRepository.findByLanguage(
                language.trim().toLowerCase(Locale.ROOT)
        );
    }

    @Override
    @Transactional
    public Joke addJoke(Joke joke) {
        if (joke == null) {
            throw new IllegalArgumentException("Joke must not be null");
        }

        if (joke.getRating() < 1 || joke.getRating() > 5) {
            throw new IllegalArgumentException("Rating must be between 1 and 5");
        }

        if (jokeRepository.existsBySetupAndPunchline(
                joke.getSetup(), joke.getPunchline()
        )) {
            throw new IllegalArgumentException("This joke already exists");
        }

        return jokeRepository.save(joke);
    }

}
