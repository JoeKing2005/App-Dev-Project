package org.example.project;

import lombok.AllArgsConstructor;
import org.example.project.repositories.JokeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("demo")
@AllArgsConstructor
public class Demo implements CommandLineRunner {

    private final JokeRepository jokeRepository;

    @Override
    public void run(String[] args) {
        System.out.println("\n--- FIND ALL ---");
        jokeRepository.findAll().forEach(System.out::println);

        System.out.println("\n--- Find Existing ID ---");
        System.out.println(jokeRepository.findById(1));

        System.out.println("\n--- FIND BY CATEGORY ---");
        System.out.println(jokeRepository.findByCategory("CODING"));

        System.out.println("\n--- FIND BY AGE ---");
        System.out.println(jokeRepository.findByAge(12));

        System.out.println("\n--- FIND BY RATING ---");
        System.out.println(jokeRepository.findByRating(2));

        System.out.println("\n--- Update Punchline ----");
        System.out.println(jokeRepository.updatePunchline(3, "They'd break it up"));

        System.out.println("\n--- DELETE EXISTING ID ----");
        boolean deleted = jokeRepository.deleteById(3);
        System.out.println(jokeRepository.findById(1));

        System.out.println("\n--- DELETE NON_EXISTING ID ---");
        System.out.println(jokeRepository.deleteById(999));

        System.out.println("\n--- Average rating ---");
        System.out.println(jokeRepository.average_rating());
    }
}
