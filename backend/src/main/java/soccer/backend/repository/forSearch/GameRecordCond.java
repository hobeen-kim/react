package soccer.backend.repository.forSearch;

import lombok.Data;

@Data
public class GameRecordCond {

    private String gameName;
    private Integer touch;
    private Integer goal;
    private Integer assist;
    private Integer chanceMaking;
    private Integer shoot;
    private Integer validShoot;
    private Integer dribble;
    private Integer successDribble;
    private Integer pass;
    private Integer successPass;
    private Integer longPass;
    private Integer successLongPass;
    private Integer crossPass;
    private Integer successCrossPass;
    private Integer tackle;
    private Integer intercept;
    private Integer contention;
    private Integer successContention;
    private Integer turnover;



}
