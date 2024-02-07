package intuit.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "player_games")
public class PlayerGames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pgID;

    @ManyToOne
    @JoinColumn(name = "playerID", nullable = false)
    private Player player;

    @ManyToOne
    @JoinColumn(name = "gameID", nullable = false)
    private Game game;

}