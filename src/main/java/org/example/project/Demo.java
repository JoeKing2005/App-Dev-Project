package org.example.project;

import lombok.AllArgsConstructor;
import org.example.project.repositories.JokeRepository;
import org.example.project.service.JokeService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("demo")
@AllArgsConstructor
public class Demo implements CommandLineRunner {

    private final JokeRepository jokeRepository;
    private final JokeService jokeService;

    @Override
    public void run(String[] args) {
        System.out.println("\n--- FIND ALL ---");
        jokeRepository.findAll().forEach(System.out::println);

        System.out.println("\n--- Find Existing ID ---");
        System.out.println(jokeRepository.findById(1));

        System.out.println("\n--- FIND BY CATEGORY ---");
        System.out.println(jokeRepository.findByCategory("CODING"));

        System.out.println("\n--- FIND BY AGE ---");
        System.out.println(jokeRepository.findByAgeAppropriatenessBefore(12));

        System.out.println("\n--- FIND BY RATING ---");
        System.out.println(jokeRepository.findByRating(2));


        System.out.println("\n--- Average rating ---");
        System.out.println(jokeRepository.averageRating());
    }
}
