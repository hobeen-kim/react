package soccer.backend.domain.game.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soccer.backend.domain.player.entity.Player;

@Getter
@Setter
@Builder
public class AllPlayersResponseDto {

    private Long id;
    private String playerName;

    public static AllPlayersResponseDto of(Player player) {
        return AllPlayersResponseDto.builder()
                .id(player.getId())
                .playerName(player.getName())
                .build();
        }
}
