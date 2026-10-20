package org.example.project.repositories;

import org.example.project.entities.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface JokeRepository extends JpaRepository<Joke, Integer> {
    List<Joke> findByCategory(String category);
    List<Joke> findByRating(int rating);
    List<Joke> findByAgeAppropriatenessBefore(int age);
    @Modifying
    @Query(value = "delete Joke x where x.ageAppropriateness=:age")
    int deleteJokesByAge(int age);
    @Query("""
        SELECT AVG(j.rating)
        FROM Joke j
        """)
    Double averageRating();
}
