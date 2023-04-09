package soccer.backend.dto.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.domain.player.Player;
import soccer.backend.dto.record.RecordResponseDto;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GamePlayerResponseDto {

        private Long id;
        private String name;
        private String position;
        private RecordResponseDto recordResponseDto;

        public static GamePlayerResponseDto of(Player player, RecordResponseDto recordResponseDto) {
            return GamePlayerResponseDto.builder()
                    .id(player.getId())
                    .name(player.getName())
                    .position(player.getPosition().toString())
                    .recordResponseDto(recordResponseDto)
                    .build();
        }


}
