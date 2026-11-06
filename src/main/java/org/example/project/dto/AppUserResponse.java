package org.example.project.dto;

public record AppUserResponse(
    Integer id,
    String email,
    String firstname,
    String lastname,
    Long jokeCount
) {}
