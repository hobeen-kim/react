package soccer.backend.dto.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.domain.info.Main;
import soccer.backend.domain.player.Position;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordRequestDto {

    private Long id;
    private Position gamePosition;
    private Integer timeIn;
    private Integer timeOut;
    private Main main;
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
