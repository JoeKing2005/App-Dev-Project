package org.example.project.dto;

import jakarta.validation.constraints.*;

public record NewJokeResponse(@NotBlank String setup, @NotBlank String punchline, @NotBlank(message = "Category is required") String category, @NotNull @Min(0) Integer ageAppropriateness, @NotNull @Min(1) @Max(5) Integer rating, @NotBlank String language, @NotNull @Positive Integer userId) {

}
