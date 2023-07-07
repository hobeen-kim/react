package soccer.backend.domain.player.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.domain.player.entity.Player;
import soccer.backend.domain.player.entity.Position;
import soccer.backend.domain.record.dto.RecordResponseDto;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlayerResponseDto {

    private Long id;
    private String name;
    private Position position;
    private List<RecordResponseDto> recordResponseDto;

    public static PlayerResponseDto of(Player player, List<RecordResponseDto> recordResponseDto) {
        return PlayerResponseDto.builder()
                .id(player.getId())
                .name(player.getName())
                .position(player.getPosition())
                .recordResponseDto(recordResponseDto)
                .build();
    }

}
