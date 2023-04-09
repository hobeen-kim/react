package soccer.backend.dto.player;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.domain.player.Player;
import soccer.backend.dto.record.RecordResponseDto;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponseDto {

    private Long id;
    private String name;
    private String position;
    private List<RecordResponseDto> recordResponseDto;

    public static PlayerResponseDto of(Player player, List<RecordResponseDto> recordResponseDto) {
        return PlayerResponseDto.builder()
                .id(player.getId())
                .name(player.getName())
                .position(player.getPosition().toString())
                .recordResponseDto(recordResponseDto)
                .build();
    }

}
