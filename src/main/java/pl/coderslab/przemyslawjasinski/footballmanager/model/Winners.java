package pl.coderslab.przemyslawjasinski.footballmanager.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "winners")
@Data
public class Winners {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String teamName;
    private Integer points;
    private Integer allScoreGoals;
    private Integer allLostGoals;
    private LocalDateTime createdAt;

    @PrePersist
    public void created() {
        LocalDateTime time = LocalDateTime.now();
        this.setCreatedAt(time);
    }
}
