package intuit.model;

import intuit.dto.PlayerDTO;
import intuit.dto.PlayerDetailsDTO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Tuple;
import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    @Query(value = "SELECT " +
            "p.playerid as playerID, " +
            "pr.name_first as nameFirst, pr.name_last as nameLast, pr.name_given as nameGiven, " +
            "bd_birth.year as birthYear, bd_birth.month as birthMonth, bd_birth.day as birthDay, " +
            "bd_death.year as deathYear, bd_death.month as deathMonth, bd_death.day as deathDay, " +
            "first_game.first_game_date as firstGameDate, " +
            "last_game.last_game_date as lastGameDate " +
            "FROM player p " +
            "INNER JOIN population_registry pr ON p.populationregistryid = pr.pid " +
            "LEFT JOIN birth_death_registry bd_birth ON pr.birth = bd_birth.bdid " +
            "LEFT JOIN birth_death_registry bd_death ON pr.death = bd_death.bdid " +
            "LEFT JOIN ( " +
            "    SELECT pg.playerid, MIN(g.date) AS first_game_date " +
            "    FROM player_games pg " +
            "    LEFT JOIN game g ON pg.gameid = g.gid " + // Use LEFT JOIN here
            "    GROUP BY pg.playerid " +
            ") first_game ON p.playerid = first_game.playerid " +
            "LEFT JOIN ( " +
            "    SELECT pg.playerid, MAX(g.date) AS last_game_date " +
            "    FROM player_games pg " +
            "    LEFT JOIN game g ON pg.gameid = g.gid " + // Use LEFT JOIN here
            "    GROUP BY pg.playerid " +
            ") last_game ON p.playerid = last_game.playerid", nativeQuery = true)
    List<Tuple> findAllPlayerDetails();

    @Query(value = "SELECT " +
            "p.playerid as playerID, " +
            "pr.name_first as nameFirst, pr.name_last as nameLast, pr.name_given as nameGiven, " +
            "bd_birth.year as birthYear, bd_birth.month as birthMonth, bd_birth.day as birthDay, " +
            "bd_death.year as deathYear, bd_death.month as deathMonth, bd_death.day as deathDay, " +
            "first_game.first_game_date as firstGameDate, " +
            "last_game.last_game_date as lastGameDate " +
            "FROM player p " +
            "INNER JOIN population_registry pr ON p.populationregistryid = pr.pid and p.playerid = :playerID " +
            "LEFT JOIN birth_death_registry bd_birth ON pr.birth = bd_birth.bdid " +
            "LEFT JOIN birth_death_registry bd_death ON pr.death = bd_death.bdid " +
            "LEFT JOIN ( " +
            "    SELECT pg.playerid, MIN(g.date) AS first_game_date " +
            "    FROM player_games pg " +
            "    LEFT JOIN game g ON pg.gameid = g.gid " + // Use LEFT JOIN here
            "    GROUP BY pg.playerid " +
            ") first_game ON p.playerid = first_game.playerid " +
            "LEFT JOIN ( " +
            "    SELECT pg.playerid, MAX(g.date) AS last_game_date " +
            "    FROM player_games pg " +
            "    LEFT JOIN game g ON pg.gameid = g.gid " + // Use LEFT JOIN here
            "    GROUP BY pg.playerid " +
            ") last_game ON p.playerid = last_game.playerid", nativeQuery = true)

    Tuple findPlayerDetailsById(@Param("playerID") Integer playerID);
}
