package org.example.project.api;

import lombok.AllArgsConstructor;
import org.example.project.dto.JokeResponse;
import org.example.project.entities.Joke;
import org.example.project.service.JokeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

@RestController
@RequestMapping("api/jokes")
@AllArgsConstructor
public class JokeController {
    private JokeService jokeService;

    @GetMapping("/{id}")
    JokeResponse getJoke(@PathVariable int id) {
        return JokeResponse.toJokeResponse(jokeService.findById(id));
    }

    @GetMapping
    public List<Joke> getJokes() {
        return jokeService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteJoke(@PathVariable int id) {
        jokeService.deleteById(id);
    }

    @GetMapping("/summary")
    public List<JokeResponse> get_setups_punchlines() {
        return jokeService.findAllSetupsAndPunchlines();
    }

    @GetMapping("/category")
    public List<Joke> getJokesByCategory(@RequestParam String category) {
        return jokeService.findByCategory(category);
    }

    @GetMapping("/age")
    public List<Joke> getJokesByAge(@RequestParam int age) {
        return jokeService.findByAge(age);
    }

    @GetMapping("/rating")
    public List<Joke> getJokesByRating(@RequestParam int rating) {
        return jokeService.findByMinimumRating(rating);
    }

    @GetMapping("/language")
    public List<Joke> getJokesByLanguage(@RequestParam String language) {
        return jokeService.findByLanguage(language);
    }
}
