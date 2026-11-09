 package org.example.project.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "jokes")
public class Joke {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String setup;
    private String punchline;
    private String category;
    private int ageAppropriateness;
    private int rating;
    private String language = "en";
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    public Joke(String setup, String punchline, String category, int age_appropriateness, int rating, String language, AppUser user) {
        this.setup = setup;
        this.punchline = punchline;
        this.category = category;
        this.ageAppropriateness = age_appropriateness;
        this.rating = rating;
        this.language = language;
        this.user = user;
    }


    @Override
    public String toString() {
        return setup + " ==> " + punchline ;}
    }



