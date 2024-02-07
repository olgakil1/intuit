package intuit.model;

import javax.persistence.*;

@Entity
@Table(name = "population_registry")
public class PopulationRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pID;

    @Column(nullable = false)
    private String nameFirst;

    @Column(nullable = false)
    private String nameLast;

    @Column(nullable = false)
    private String nameGiven;

    @OneToOne
    @JoinColumn(name = "birth", nullable = false)
    private BirthDeathRegistry birth;

    @OneToOne
    @JoinColumn(name = "death")
    private BirthDeathRegistry death;

}