package org.example.project.api;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.project.dto.JokeResponse;
import org.example.project.dto.JokeSummaryResponse;
import org.example.project.dto.NewJokeResponse;
import org.example.project.dto.UpdateRatingResponse;
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
    public List<JokeSummaryResponse> get_setups_punchlines() {
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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public JokeResponse addJoke(@Valid @RequestBody NewJokeResponse request) {
        Joke joke = new Joke();
        joke.setSetup(request.setup());
        joke.setPunchline(request.punchline());
        joke.setCategory(request.category());
        joke.setAgeAppropriateness(request.ageAppropriateness());
        joke.setRating(request.rating());
        joke.setLanguage(request.language());

        Joke saved = jokeService.addJoke(joke, request.userId());

        return JokeResponse.toJokeResponse(saved);
    }

    @PatchMapping("/{id}/rating")
    public JokeResponse updateRating(@PathVariable int id, @Valid @RequestBody UpdateRatingResponse request) {
        Joke updated = jokeService.updateRating(id, request.rating());

        return JokeResponse.toJokeResponse(updated);
    }
}
