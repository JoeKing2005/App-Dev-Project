package org.example.project.repositories;

import org.example.project.dto.AppUserResponse;
import org.example.project.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {

    @Query("""
            SELECT new org.example.project.dto.AppUserResponse(
                        a.email, a.firstName, a.lastName, COUNT(j.id)
                        )
                       FROM AppUser a 
                       LEFT JOIN Joke j on j.user = a
                       GROUP BY a.userId, a.email, a.firstName, a.lastName
                                   """)
    List<AppUserResponse> findAllUsers();

    @Query("""
    SELECT new org.example.project.dto.AppUserResponse(
        a.email, a.firstName, a.lastName, COUNT(j.id)
        )
            FROM AppUser a
                LEFT JOIN Joke j on j.user = a
                    WHERE a.userId = :id
                        GROUP BY a.userId, a.email, a.firstName, a.lastName
        """)
    Optional<AppUserResponse> findUserResponseById(@Param("id") int id);

}
