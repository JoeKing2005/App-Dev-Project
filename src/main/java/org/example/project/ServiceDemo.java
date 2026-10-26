package org.example.project;

import lombok.AllArgsConstructor;
import org.example.project.entities.Joke;
import org.example.project.service.JokeNotFoundException;
import org.example.project.service.JokeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("service-demo")
@AllArgsConstructor
public class ServiceDemo implements CommandLineRunner {
    private final JokeService jokeService;

    private Joke createJoke(
            String setup, String punchline, int rating, String language
    ) {
        Joke joke = new Joke();
        joke.setSetup(setup);
        joke.setPunchline(punchline);
        joke.setCategory("CODING");
        joke.setAgeAppropriateness(10);
        joke.setRating(rating);
        joke.setLanguage(language);
        return joke;
    }


    @Override
    public void run(String[] args){
        System.out.println("\n--- SUCCESSFUL ADD ---");
        Joke saved = jokeService.addJoke(createJoke(
                "Why did the programmer quit his job?",
                "Because he didn't get arrays.",
                4,
                "en"
        ));
        System.out.println("Added: " + saved);

        System.out.println("\n--- DUPLICATE JOKE ---");
        try {
            jokeService.addJoke(createJoke(
                    "Why did the programmer quit his job?",
                    "Because he didn't get arrays.",
                    4,
                    "en"
            ));
            System.out.println("FAIL: duplicate was accepted");
        } catch (IllegalArgumentException ex) {
            System.out.println("Duplicate rejected: " + ex.getMessage());
        }

        System.out.println("\n--- INVALID RATING ---");
        try {
            jokeService.addJoke(createJoke(
                    "An invalid demo joke",
                    "This should not be saved.",
                    9,
                    "en"
            ));
            System.out.println("FAIL: invalid joke was accepted");
        } catch (IllegalArgumentException ex) {
            System.out.println("Invalid joke rejected: " + ex.getMessage());
        }

        System.out.println("\n--- NON-EXISTING ID ---");
        try {
            jokeService.findById(-1);
            System.out.println("FAIL: missing joke was accepted");
        } catch (JokeNotFoundException ex) {
            System.out.println("Missing joke handled: " + ex.getMessage());
        }

        System.out.println("\n--- Spanish Jokes ---");
        jokeService.findByLanguage("es").forEach(System.out::println);

    }
}
