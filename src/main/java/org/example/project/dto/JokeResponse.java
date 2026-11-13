package org.example.project.dto;


import org.example.project.entities.Joke;

public record JokeResponse( String setup, String punchline, String category, int ageAppropriateness, int rating, String language, AppUserResponse user) {
    public static JokeResponse toJokeResponse(Joke joke) {
        var owner = joke.getUser();
        return new JokeResponse(
                joke.getSetup(),
                joke.getPunchline(),
                joke.getCategory(),
                joke.getAgeAppropriateness(),
                joke.getRating(),
                joke.getLanguage(),
                new AppUserResponse(
                        owner.getEmail(),
                        owner.getFirstName(),
                        owner.getLastName(),
                        null
                )
        );
    }
}
