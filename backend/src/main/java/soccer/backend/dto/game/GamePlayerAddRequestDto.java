package soccer.backend.dto.game;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.backend.domain.info.Main;
import soccer.backend.domain.player.Position;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GamePlayerAddRequestDto {

    private Long playerId;
    private Position gamePosition;
    private Integer timeIn;
    private Integer timeOut;
    private Main main;
}
