package org.example.project;

import lombok.RequiredArgsConstructor;
import org.example.project.entities.Joke;
import org.example.project.repositories.JokeRepository;
import org.example.project.service.AppUserService;
import org.example.project.service.JokeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("user-demo")
@RequiredArgsConstructor
public class AppUserDemo implements CommandLineRunner {

    private final AppUserService appUserService;
    private final JokeService jokeService;
    private final JokeRepository jokeRepository;

    @Override
    public void run(String... args) {

        System.out.println("\n ALL USERS AND JOKE COUNTS ");
        appUserService.findAll().forEach(System.out::println);

        System.out.println("\n FIND USER 1");
        System.out.println(appUserService.findById(1));

        System.out.println("\n USER 1's JOKES");
        appUserService.findJokesByUserId(1)
                .forEach(System.out::println);

        System.out.println("\n ADD JOKE FOR USER 1");
        Joke saved = jokeService.addJoke(newJoke(), 1);
        System.out.println("Added: " + saved);

        System.out.println("\n UPDATED USER 1 JOKE COUNT");
        System.out.println(appUserService.findById(1));

        System.out.println("\n ADD JOKE FOR MISSING USER");
        try {
            Joke joke = newJoke();
            joke.setSetup("Why did the computer bring an umbrella?");
            joke.setPunchline("It expected cloud computing.");

            jokeService.addJoke(joke, 3);
            System.out.println("Fail: missing user was accepted");
        } catch (IllegalArgumentException ex) {
            System.out.println("Rejected: " + ex.getMessage());
        }

        System.out.println("\n FIND MISSING USER");
        try {
            appUserService.findById(-1);
            System.out.println("FAIL: missing user was accepted");
        } catch (IllegalArgumentException ex) {
            System.out.println("Missing user handled: " + ex.getMessage());
        }

        System.out.println("\n AGE 12, MINIMUM RATING 4");
        jokeRepository.findByRatingGreaterThanEqualAndAgeAppropriatenessLessThanEqual(4,12).forEach(joke -> {
            System.out.println("Setup: " + joke.setup());
            System.out.println("Punchline: " + joke.punchline());
        });

        System.out.println("\n DELETE USER 2 AND THEIR JOKES");
        appUserService.deleteAccount(2);
        System.out.println("Account deleted");

        System.out.println("\n REMAINING USERS");
        appUserService.findAll().forEach(System.out::println);
    }

    private Joke newJoke() {
        Joke joke = new Joke();
        joke.setSetup("Why did the programmer bring a ladder?");
        joke.setPunchline("To reach a higher level of abstraction");
        joke.setCategory("CODING");
        joke.setAgeAppropriateness(10);
        joke.setRating(4);
        joke.setLanguage("en");
        return joke;
    }
}
