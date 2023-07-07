package soccer.backend.domain.game.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.domain.player.entity.Player;
import soccer.backend.domain.record.dto.RecordResponseDto;

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
