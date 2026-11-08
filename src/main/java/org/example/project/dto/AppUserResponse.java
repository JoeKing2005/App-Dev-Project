package org.example.project.dto;

public record AppUserResponse(
    String email,
    String firstname,
    String lastname,
    Long jokeCount
) {}
