package org.example.project.service;

import org.example.project.dto.AppUserResponse;
import org.example.project.entities.Joke;

import java.util.List;

public interface AppUserService {
    List<AppUserResponse> findAll();
    AppUserResponse findById(int id);
    List<Joke> findJokesByUserId(int id);
    void deleteAccount(int id);
}
