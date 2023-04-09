package soccer.backend.dto.game.gameField;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.player.Player;
import soccer.backend.dto.dotRecord.DotRecordResponseDto;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Builder
@Getter
@Setter
public class GameFieldResponseDto {
    private Long id;
    private String gameName;
    private String opponent;
    private String location;
    private Integer GF;
    private Integer GA;
    private LocalDate createdAt;
    private List<DotRecordResponseDto> dotRecordResponseDto;
    private Map<Long, String> allPlayers;

    public static GameFieldResponseDto of(Game game, List<Player> players, List<DotRecordResponseDto> dotRecordResponseDto) {

        Map<Long, String> allPlayers = new HashMap<>();
        for (Player player : players) {
            allPlayers.put(player.getId(), player.getName());
        }

        return GameFieldResponseDto.builder()
                .id(game.getId())
                .gameName(game.getGameName())
                .opponent(game.getOpponent())
                .location(game.getLocation())
                .GF(game.getGF())
                .GA(game.getGA())
                .createdAt(game.getCreatedAt())
                .dotRecordResponseDto(dotRecordResponseDto)
                .allPlayers(allPlayers)
                .build();
    }

}
