package intuit.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "player")
public class Player {

    @Id
    @Column(nullable = false)
    private Long playerID;

    @ManyToOne
    @JoinColumn(name = "populationRegistryID", nullable = false)
    private PopulationRegistry populationRegistry;

    @Column(nullable = false)
    private String retroID;

    @Column(nullable = false)
    private String bbrefID;

}