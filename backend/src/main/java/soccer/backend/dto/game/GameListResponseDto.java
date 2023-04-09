package soccer.backend.dto.game;

import lombok.*;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.player.Player;
import soccer.backend.repository.PlayerRepository;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GameListResponseDto {

    private Long id;
    private String gameName;
    private String opponent;
    private String location;
    private Integer GF;
    private Integer GA;
    private LocalDate createdAt;
    private List<GamePlayerResponseDto> gamePlayerResponseDto;
    private Map<Long, String> allPlayers;

    public static GameListResponseDto of(Game game, List<Player> players, List<GamePlayerResponseDto> gamePlayerResponseDto) {

        Map<Long, String> allPlayers = new HashMap<>();
        for (Player player : players) {
            allPlayers.put(player.getId(), player.getName());
        }

        return GameListResponseDto.builder()
                .id(game.getId())
                .gameName(game.getGameName())
                .opponent(game.getOpponent())
                .location(game.getLocation())
                .GF(game.getGF())
                .GA(game.getGA())
                .createdAt(game.getCreatedAt())
                .gamePlayerResponseDto(gamePlayerResponseDto)
                .allPlayers(allPlayers)
                .build();
    }



}
