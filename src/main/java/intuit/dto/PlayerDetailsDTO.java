package intuit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PlayerDetailsDTO {

    private Integer playerID;
    private String nameFirst;
    private String nameLast;
    private String nameGiven;
    private Integer birthYear;
    private Integer birthMonth;
    private Integer birthDay;
    private Integer deathYear;
    private Integer deathMonth;
    private Integer deathDay;
    private Date firstGameDate;
    private Date lastGameDate;

  }