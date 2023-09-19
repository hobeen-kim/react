package soccer.backend.dto.game;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.backend.global.annotation.MinMax120;
import soccer.backend.domain.info.Main;
import soccer.backend.domain.player.Position;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GamePlayerAddRequestDto {

    private Long playerId;
    @NotNull(message="포지션을 확인해주세요.")
    private Position gamePosition;
    @MinMax120
    private int timeIn;
    @MinMax120
    private int timeOut;
    @NotNull(message="선발/교체 여부를 확인해주세요.")
    private Main main;
}
