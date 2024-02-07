

import intuit.controllers.PlayerController;
import intuit.dto.PlayerDetailsDTO;
import intuit.services.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.anyString;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    @Test
    public void testUnauthorizedRequest(){
        //TODO
    }
    @Test
    public void listPlayers_ShouldReturnPlayerNotDead() throws Exception {
        // Arrange
        PlayerDetailsDTO playerOne = new PlayerDetailsDTO(
                1,"p1","p1","p1",1978,1,1,null,null,null,null,null);

        PlayerDetailsDTO playerTwo = new PlayerDetailsDTO(
                1,"p3","p4","p3",1978,1,1,2050,1,1,null,null);

        List<PlayerDetailsDTO> playerList = Arrays.asList(playerOne, playerTwo);

        when(playerService.getPlayers()).thenReturn(playerList);

        // Act & Assert
        mockMvc.perform(get("/api/players")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerID").value(playerOne.getPlayerID()))
                .andExpect(jsonPath("$[1].playerID").value(playerTwo.getPlayerID()));
    }

    @Test
    public void listPlayers_ShouldReturnPlayerNoGames() throws Exception {
        // Arrange
        PlayerDetailsDTO playerOne = new PlayerDetailsDTO(
                1,"p1","p1","p1",1978,1,1,2050,1,1,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));

        PlayerDetailsDTO playerTwo = new PlayerDetailsDTO(
                1,"p3","p4","p3",1978,1,1,2050,1,1,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));

        List<PlayerDetailsDTO> playerList = Arrays.asList(playerOne, playerTwo);

        when(playerService.getPlayers()).thenReturn(playerList);

        // Act & Assert
        mockMvc.perform(get("/api/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerID").value(playerOne.getPlayerID()))
                .andExpect(jsonPath("$[1].playerID").value(playerTwo.getPlayerID()));
    }

    @Test
    public void listPlayers_ShouldReturnPlayerListAllDataExist() throws Exception {
        // Arrange
        PlayerDetailsDTO playerOne = new PlayerDetailsDTO(
                1,"p1","p1","p1",1978,1,1,2050,1,1,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));

        PlayerDetailsDTO playerTwo = new PlayerDetailsDTO(
                1,"p3","p4","p3",1978,1,1,2050,1,1,new Date(System.currentTimeMillis()),new Date(System.currentTimeMillis()));

        List<PlayerDetailsDTO> playerList = Arrays.asList(playerOne, playerTwo);

        when(playerService.getPlayers()).thenReturn(playerList);

        // Act & Assert
        mockMvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].playerID").value(playerOne.getPlayerID()))
                .andExpect(jsonPath("$[1].playerID").value(playerTwo.getPlayerID()));
    }

    @Test
    public void getPlayerById_WhenPlayerExists_ShouldReturnPlayer() throws Exception {
        // Arrange
        String playerId = "123";
        PlayerDetailsDTO playerDetailsDTO = new PlayerDetailsDTO(/* initialize with test data */);

        when(playerService.get(anyInt())).thenReturn(Optional.of(playerDetailsDTO));

        // Act & Assert
        mockMvc.perform(get("/api/players/{playerId}", playerId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.playerID").value(playerDetailsDTO.getPlayerID()));
    }

    @Test
    public void getPlayerById_WhenPlayerDoesNotExist_ShouldReturnNotFound() throws Exception {
        // Arrange
        String playerId = "123";

        when(playerService.get(anyInt())).thenReturn(Optional.empty());

        // Act & Assert
        mockMvc.perform(get("/api/players/{playerId}", playerId))
                .andExpect(status().isNotFound());
    }
}