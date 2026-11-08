package org.example.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.project.dto.AppUserResponse;
import org.example.project.entities.AppUser;
import org.example.project.entities.Joke;
import org.example.project.repositories.AppUserRepository;
import org.example.project.repositories.JokeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppUserServiceImpl implements AppUserService{

    private final AppUserRepository appUserRepository;
    private final JokeRepository jokeRepository;

    @Override
    public List<AppUserResponse> findAll() {
        return appUserRepository.findAllUsers();
    };

    @Override
    public AppUserResponse findById(int id) {
        return appUserRepository.findUserResponseById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with ID " + id + "was not found"
                ));
    }

    private AppUser requireUser(int id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with ID " + id + "was not found"
                ));
    }

    @Override
    public List<Joke> findJokesByUserId(int id) {
        requireUser(id);
        return jokeRepository.findByUser_UserId(id);
    }

    @Override
    @Transactional
    public void deleteAccount(int id) {
        requireUser(id);

        jokeRepository.deleteAllByUserId(id);
        appUserRepository.deleteById(id);
    }

}
