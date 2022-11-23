package pl.coderslab.przemyslawjasinski.footballmanager.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;


@Entity
@Table(name = "teams")
@Data
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 3)
    private String name;
    private Integer points;
    private Integer wins;
    private Integer draws;
    private Integer lost;
    private Integer allScoresGoals;
    private Integer allLostGoals;

    @ManyToOne
    private Stadium stadium;

//    @OneToMany
//    private List<Match> matches;

    public Team() {
        reset();
    }

    public Team(String name) {
        this();
        this.name = name;
    }

    public Integer getMatchesPlayed() {
        return wins + draws + lost;
    }

    public void reset() {
        this.wins = 0;
        this.draws = 0;
        this.lost = 0;
        this.allLostGoals = 0;
        this.allScoresGoals = 0;
        this.points = 0;
    }
}
