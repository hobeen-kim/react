package soccer.backend.dto.dotRecord;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.backend.domain.info.DotRecord;
import soccer.backend.domain.player.Player;
import soccer.backend.domain.player.Position;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DotRecordResponseDto {

    private Long PlayerId;
    private String playerName;
    private Position gamePosition;
    private Float x;
    private Float y;
    private boolean shoot;
    private boolean validShoot;
    private Float shootX;
    private Float shootY;
    private Integer gameTime;

    public DotRecordResponseDto(DotRecord dotRecord){
        this.PlayerId = dotRecord.getPlayerId();
        this.playerName = dotRecord.getPlayerName();
        this.gamePosition = dotRecord.getGamePosition();
        this.x = dotRecord.getX();
        this.y = dotRecord.getY();
        this.shoot = dotRecord.isShoot();
        this.validShoot = dotRecord.isValidShoot();
        this.shootX = dotRecord.getShootX();
        this.shootY = dotRecord.getShootY();
        this.gameTime = dotRecord.getGameTime();
    }

    public static List<DotRecordResponseDto> toDotRecordResponseDtoList(List<DotRecord> dotRecords){
        List<DotRecordResponseDto> dotRecordResponseDtoList = new ArrayList<>();
        for (DotRecord dotRecord : dotRecords) {
            DotRecordResponseDto dotRecordResponseDto = new DotRecordResponseDto(dotRecord);
            dotRecordResponseDtoList.add(dotRecordResponseDto);
        }
        return dotRecordResponseDtoList;
    }

    //player 와 dotRecordResponseDto 전체를 받아서 player의 dotRecordResponseDto를 반환해주는 메서드
    public static List<DotRecordResponseDto> toPlayerDotRecordDtoList(Player player, List<DotRecordResponseDto> dotRecordResponseDto){
        List<DotRecordResponseDto> playerDotRecordDtoList = new ArrayList<>();
        for (DotRecordResponseDto dto : dotRecordResponseDto) {
            if(dto.getPlayerId() == player.getId()){
                playerDotRecordDtoList.add(dto);
            }
        }
        return playerDotRecordDtoList;
    }

}
