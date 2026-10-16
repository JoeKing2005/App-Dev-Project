package org.example.project.dto;


import org.example.project.entities.Joke;

public record JokeResponse(String setup, String punchline) {
    public static JokeResponse toJokeResponse(Joke joke) {
        return new JokeResponse(
                joke.getSetup(),
                joke.getPunchline()
        );
    }
}
