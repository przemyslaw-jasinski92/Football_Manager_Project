package pl.coderslab.przemyslawjasinski.footballmanager.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "matches")
@Data
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Team homeTeam;
    @ManyToOne
    private Team awayTeam;

    @Digits(integer = 2, fraction = 0)
    @Min(value = 0)
    private Integer homeTeamGoals;

    @Digits(integer = 2, fraction = 0)
    @Min(value = 0)
    private Integer awayTeamGoals;

    @ManyToOne(cascade = CascadeType.REMOVE)
    private Round round;

    private String updatedAt;

    @PreUpdate
    public void update() {
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        this.setUpdatedAt(time.format(formatter));
    }

}
