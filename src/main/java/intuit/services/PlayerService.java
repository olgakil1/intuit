package intuit.services;

import intuit.dto.PlayerDetailsDTO;
import intuit.model.PlayerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.Tuple;
import javax.validation.constraints.NotBlank;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    public List<PlayerDetailsDTO> getPlayers() {
        List<Tuple> resultTuples = playerRepository.findAllPlayerDetails();
        List<PlayerDetailsDTO> playerDetailsList = new ArrayList<>();

        for (Tuple result : resultTuples) {
            PlayerDetailsDTO dto = getPlayerDetailsDTO(result);

            playerDetailsList.add(dto);
        }
        return playerDetailsList;
    }

    private static PlayerDetailsDTO getPlayerDetailsDTO(Tuple result) {
        PlayerDetailsDTO dto = new PlayerDetailsDTO(
                result.get("playerID", Integer.class),
                result.get("nameFirst", String.class),
                result.get("nameLast", String.class),
                result.get("nameGiven", String.class),
                result.get("birthYear", Integer.class),
                result.get("birthMonth", Integer.class),
                result.get("birthDay", Integer.class),
                // Use `get` method's default value feature for integer types to avoid null
                // If the year, month, or day is null, it will use `null`
                result.get("deathYear", Integer.class),
                result.get("deathMonth", Integer.class),
                result.get("deathDay", Integer.class),
                result.get("firstGameDate", Date.class),
                result.get("lastGameDate", Date.class));
        return dto;
    }


    public Optional<PlayerDetailsDTO> get(@NotBlank Integer playerId) {
        Tuple result = playerRepository.findPlayerDetailsById(playerId);
        if(result != null && result.getElements().size()>0){
            return Optional.ofNullable(getPlayerDetailsDTO(result));
        }
        return null;
    }
}
