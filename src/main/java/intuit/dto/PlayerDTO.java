package intuit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerDTO {
    private Integer playerID;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
}
