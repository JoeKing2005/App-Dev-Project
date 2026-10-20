package org.example.project.entities;


import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
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

    public Joke(String setup, String punchline, String category, int age_appropriateness, int rating) {
        this.setup = setup;
        this.punchline = punchline;
        this.category = category;
        this.ageAppropriateness = age_appropriateness;
        this.rating = rating;
    }


    @Override
    public String toString() {
        return setup + " ==> " + punchline ;}
    }



