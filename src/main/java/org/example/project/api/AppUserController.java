package org.example.project.api;

import lombok.AllArgsConstructor;
import org.example.project.dto.AppUserDetailsResponse;
import org.example.project.dto.AppUserResponse;
import org.example.project.dto.JokeResponse;
import org.example.project.entities.AppUser;
import org.example.project.service.AppUserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@AllArgsConstructor
public class AppUserController {
    private AppUserService appUserService;

    @GetMapping("/{id}")
    public AppUserDetailsResponse getUser(@PathVariable int id) { return appUserService.findById(id);
    }

    @GetMapping
    public List<AppUserDetailsResponse> getUsers() {
        return appUserService.findAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable int id) {
        appUserService.deleteAccount(id);
    }

    @GetMapping("/{id}/jokes")
    public List<JokeResponse> getUserJokes(@PathVariable int id) {
        return appUserService.findJokesByUserId(id).stream().map(JokeResponse::toJokeResponse).toList();
    }

    @GetMapping("/summary")
    public List<AppUserResponse> getUserSummary() {
        return appUserService.findAllSummaries();
    }

}
