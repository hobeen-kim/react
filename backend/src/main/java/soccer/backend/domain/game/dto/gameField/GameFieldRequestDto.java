package soccer.backend.domain.game.dto.gameField;

import lombok.*;
import soccer.backend.domain.dotrecord.dto.DotRecordRequestDto;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameFieldRequestDto {

    private DotRecordRequestDto[] dotRecordRequestDto;

}
