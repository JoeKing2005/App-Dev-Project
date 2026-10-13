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


    public Joke(String setup, String punchline, String category) {
        this.setup = setup;
        this.punchline = punchline;
        this.category = category;
    }


    @Override
    public String toString() {
        return setup + " ==> " + punchline ;}
    }



