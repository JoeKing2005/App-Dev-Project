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
    public JokeResponse getJoke(@PathVariable int id) {
        return JokeResponse.toJokeResponse(jokeService.findById(id));
    }

    @GetMapping
    public List<JokeResponse> getJokes() {
        return jokeService.findAll().stream().map(JokeResponse::toJokeResponse).toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteJoke(@PathVariable int id) {
        jokeService.deleteById(id);
    }

    @GetMapping("/summary")
    public List<JokeResponse> get_setups_punchlines() {
        return jokeService.findAllSetupsAndPunchlines();
    }

    @GetMapping("/category")
    public List<JokeResponse> getJokesByCategory(@RequestParam String category) {
        return jokeService.findByCategory(category).stream().map(JokeResponse::toJokeResponse).toList();
    }

    @GetMapping("/age")
    public List<JokeResponse> getJokesByAge(@RequestParam int age) {
        return jokeService.findByAge(age).stream().map(JokeResponse::toJokeResponse).toList();
    }

    @GetMapping("/rating")
    public List<JokeResponse> getJokesByRating(@RequestParam int rating) {
        return jokeService.findByMinimumRating(rating).stream().map(JokeResponse::toJokeResponse).toList();
    }

    @GetMapping("/language")
    public List<JokeResponse> getJokesByLanguage(@RequestParam String language) {
        return jokeService.findByLanguage(language).stream().map(JokeResponse::toJokeResponse).toList();
    }
}
