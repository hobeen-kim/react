package soccer.backend.dto.game;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import soccer.backend.domain.member.Member;
import soccer.backend.domain.player.Player;

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
