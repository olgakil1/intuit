

import intuit.model.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PlayerRepositoryTest {

    @Autowired
    private PlayerRepository playerRepository;

    @Test
    void findAllPlayerDetails_ShouldReturnAllPlayersDetails() {
        // Test logic to verify the actual results against expected results
    }

    @Test
    void findPlayerDetailsById_ShouldReturnPlayerDetails() {
        // Test logic similar to above
    }
}
