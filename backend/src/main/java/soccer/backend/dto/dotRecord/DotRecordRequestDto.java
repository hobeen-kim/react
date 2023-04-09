package soccer.backend.dto.dotRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.backend.domain.player.Position;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DotRecordRequestDto {

    private Long playerId;
    private String playerName;
    private Float x;
    private Float y;
    private Position gamePosition;
    private boolean shoot;
    private boolean validShoot;
    private Float shootX;
    private Float shootY;
    private Integer gameTime;
}
