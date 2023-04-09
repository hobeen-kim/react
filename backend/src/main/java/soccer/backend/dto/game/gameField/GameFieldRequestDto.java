package soccer.backend.dto.game.gameField;

import lombok.*;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.info.DotRecord;
import soccer.backend.dto.dotRecord.DotRecordRequestDto;
import soccer.backend.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameFieldRequestDto {

    private Long id;
    private DotRecordRequestDto[] dotRecordRequestDto;

}
