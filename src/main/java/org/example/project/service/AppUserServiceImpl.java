package org.example.project.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.project.dto.AppUserDetailsResponse;
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
    public List<AppUserDetailsResponse> findAll() {
        return appUserRepository.findAll().stream().map(this::toDetails).toList();
    };

    @Override
    public AppUserDetailsResponse findById(int id) {
        return toDetails(requireUser(id));
    }

    private AppUser requireUser(int id) {
        return appUserRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException(
                        "User with ID " + id + "was not found"
                ));
    }

    public List<AppUserResponse> findAllSummaries() {
        return appUserRepository.findAllUsers();
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

    private AppUserDetailsResponse toDetails(AppUser user) {
        return new AppUserDetailsResponse(
                user.getUserId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName()
        );
    }

}
