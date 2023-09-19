package soccer.backend.dto.record;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.global.annotation.MinMax120;
import soccer.backend.domain.info.Main;
import soccer.backend.domain.player.Position;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordRequestDto {

    private Long id;
    @NotNull(message="포지션을 확인해주세요.")
    private Position gamePosition;
    @MinMax120
    private int timeIn;
    @MinMax120
    private int timeOut;
    @NotNull(message="선발/교체 여부를 확인해주세요.")
    private Main main;
    @MinMax120
    private int touch;
    @MinMax120
    private int goal;
    @MinMax120
    private int assist;
    @MinMax120
    private int chanceMaking;
    @MinMax120
    private int shoot;
    @MinMax120
    private int validShoot;
    @MinMax120
    private int dribble;
    @MinMax120
    private int successDribble;
    @MinMax120
    private int pass;
    @MinMax120
    private int successPass;
    @MinMax120
    private int longPass;
    @MinMax120
    private int successLongPass;
    @MinMax120
    private int crossPass;
    @MinMax120
    private int successCrossPass;
    @MinMax120
    private int tackle;
    @MinMax120
    private int intercept;
    @MinMax120
    private int contention;
    @MinMax120
    private int successContention;
    @MinMax120
    private int turnover;
}
