package intuit.controllers;

import intuit.dto.PlayerDetailsDTO;
import intuit.services.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/players")
public class PlayerController extends BaseController{

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerDetailsDTO>> listPlayers() {
        return ResponseEntity.of(java.util.Optional.ofNullable(playerService.getPlayers()));
    }

    @GetMapping("/{playerId}")
    public ResponseEntity<PlayerDetailsDTO> getPlayerById(@PathVariable Integer playerId) {
        Optional<PlayerDetailsDTO> player = this.playerService.get(playerId);
        if (Optional.ofNullable(player).isPresent() && Optional.of(player).get().isPresent()){
            return ResponseEntity.ok(player.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}