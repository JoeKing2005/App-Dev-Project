package org.example.project.dto;

import jakarta.validation.constraints.*;

public record UpdateRatingResponse(@NotNull @Min(1) @Max(5) Integer rating) {
}
