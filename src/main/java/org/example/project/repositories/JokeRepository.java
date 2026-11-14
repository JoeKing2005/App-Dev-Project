package org.example.project.repositories;

import org.example.project.dto.JokeSummaryResponse;
import org.example.project.entities.Joke;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.example.project.dto.JokeResponse;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface JokeRepository extends JpaRepository<Joke, Integer> {
    List<Joke> findByCategory(String category);
    List<Joke> findByRatingGreaterThanEqual(int rating);
    List<Joke> findByAgeAppropriatenessLessThanEqual(int age);
    @Modifying
    @Query(value = "delete Joke x where x.ageAppropriateness=:age")
    int deleteJokesByAge(int age);
    @Query("""
        SELECT AVG(j.rating)
        FROM Joke j
        """)
    Double averageRating();

    @Query("""
        SELECT new org.example.project.dto.JokeSummaryResponse(j.setup, j.punchline)
        FROM Joke j
            """)
    List<JokeSummaryResponse> findALlProjectedBy();
    
    @Modifying
    @Query("DELETE FROM Joke j WHERE j.category = :category")
    int deleteJokeByCategory(
            @org.springframework.data.repository.query.Param("category")
            String category
    );

    List<Joke> findByUser_UserId(int userId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Query("DELETE from Joke j WHERE j.user.userId = :userId")
    int deleteAllByUserId(@Param("userId") Integer userId);
    List<JokeResponse> findByRatingGreaterThanEqualAndAgeAppropriatenessLessThanEqual(int rating, int age);
    boolean existsBySetupAndPunchline(String setup, String punchline);

    List<Joke> findByAgeAppropriatenessBefore(int i);
    List<Joke> findByLanguage(String language);
}
