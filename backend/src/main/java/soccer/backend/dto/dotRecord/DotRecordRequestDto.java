package soccer.backend.dto.dotRecord;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.backend.annotation.MinMax120;
import soccer.backend.domain.player.Position;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DotRecordRequestDto {

    private Long playerId;
    @Pattern(regexp = "^[a-zA-Z가-힣]{1,100}$", message="1~100글자의 영문자 또는 한글을 입력해주세요.")
    private String playerName;
    private Float x;
    private Float y;
    @NotNull(message="포지션을 확인해주세요.")
    private Position gamePosition;
    private boolean shoot;
    private boolean validShoot;
    private Float shootX;
    private Float shootY;
    @MinMax120
    private Integer gameTime;
}
