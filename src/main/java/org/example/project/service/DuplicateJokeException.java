package org.example.project.service;

public class DuplicateJokeException extends RuntimeException {
    public DuplicateJokeException(String message) {
        super(message);
    }
}
