package pl.coderslab.przemyslawjasinski.footballmanager.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "stadiums")
@Data
public class Stadium {
    @Id
    @GeneratedValue
    private Long id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String country;
    @NotEmpty
    private String city;
    @NotEmpty
    private String address;

}
