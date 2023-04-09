package soccer.backend.dto.record;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.domain.info.Main;
import soccer.backend.domain.info.Record;
import soccer.backend.domain.player.Position;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordResponseDto {

    private Long playerId;
    private Long gameId;
    private String playerName;
    private String gameName;
    private LocalDate createdAt;
    private Position gamePosition;
    private Integer timeIn;
    private Integer timeOut;
    private Main main;
    private Integer touch;
    private Integer goal;
    private Integer assist;
    private Integer chanceMaking;
    private Integer shoot;
    private Integer validShoot;
    private Integer dribble;
    private Integer successDribble;
    private Integer pass;
    private Integer successPass;
    private Integer longPass;
    private Integer successLongPass;
    private Integer crossPass;
    private Integer successCrossPass;
    private Integer tackle;
    private Integer intercept;
    private Integer contention;
    private Integer successContention;
    private Integer turnover;

    public RecordResponseDto(Record record){
        this.playerId = record.getPlayer().getId();
        this.gameId = record.getGame().getId();
        this.playerName = record.getPlayer().getName();
        this.createdAt = record.getGame().getCreatedAt();
        this.gameName = record.getGame().getGameName();
        this.gamePosition = record.getGamePosition();
        this.timeIn = record.getTimeIn();
        this.timeOut = record.getTimeOut();
        this.main = record.getMain();
        this.touch = record.getTouch();
        this.goal = record.getGoal();
        this.assist = record.getAssist();
        this.chanceMaking = record.getChanceMaking();
        this.shoot = record.getShoot();
        this.validShoot = record.getValidShoot();
        this.dribble = record.getDribble();
        this.successDribble = record.getSuccessDribble();
        this.pass = record.getPass();
        this.successPass = record.getSuccessPass();
        this.longPass = record.getLongPass();
        this.successLongPass = record.getSuccessLongPass();
        this.crossPass = record.getCrossPass();
        this.successCrossPass = record.getSuccessCrossPass();
        this.tackle = record.getTackle();
        this.intercept = record.getIntercept();
        this.contention = record.getContention();
        this.successContention = record.getSuccessContention();
        this.turnover = record.getTurnover();
    }
}
