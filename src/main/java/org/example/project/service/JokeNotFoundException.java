package org.example.project.service;

public class JokeNotFoundException extends RuntimeException {
    public JokeNotFoundException(String message) {
        super(message);
    }
}
