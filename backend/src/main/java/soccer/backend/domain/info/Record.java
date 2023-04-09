package soccer.backend.domain.info;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.player.Player;
import soccer.backend.domain.player.Position;
import soccer.backend.dto.record.RecordRequestDto;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //players
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    @JsonBackReference
    private Player player;

    //games
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    @JsonBackReference
    private Game game;

    public Record(Game game, Player player, Position gamePosition, Main main) {
        this.game = game;
        this.player = player;
        this.gamePosition = gamePosition;
        this.main = main;
        this.timeIn = 0;
        this.timeOut = 0;
        this.touch = 0;
        this.goal = 0;
        this.assist = 0;
        this.chanceMaking = 0;
        this.shoot = 0;
        this.validShoot = 0;
        this.dribble = 0;
        this.successDribble = 0;
        this.pass = 0;
        this.successPass = 0;
        this.longPass = 0;
        this.successLongPass = 0;
        this.crossPass = 0;
        this.successCrossPass = 0;
        this.tackle = 0;
        this.intercept = 0;
        this.contention = 0;
        this.successContention = 0;
        this.turnover = 0;
    }

    @Enumerated(EnumType.STRING)
    private Position gamePosition;
    private Integer timeIn;
    private Integer timeOut;
    @Enumerated(EnumType.STRING)
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

    public static Record updateRecord (Record record, RecordRequestDto recordRequestDto) {
        record.gamePosition = recordRequestDto.getGamePosition();
        record.timeIn = recordRequestDto.getTimeIn();
        record.timeOut = recordRequestDto.getTimeOut();
        record.main = recordRequestDto.getMain();
        record.touch = recordRequestDto.getTouch();
        record.goal = recordRequestDto.getGoal();
        record.assist = recordRequestDto.getAssist();
        record.chanceMaking = recordRequestDto.getChanceMaking();
        record.shoot = recordRequestDto.getShoot();
        record.validShoot = recordRequestDto.getValidShoot();
        record.dribble = recordRequestDto.getDribble();
        record.successDribble = recordRequestDto.getSuccessDribble();
        record.pass = recordRequestDto.getPass();
        record.successPass = recordRequestDto.getSuccessPass();
        record.longPass = recordRequestDto.getLongPass();
        record.successLongPass = recordRequestDto.getSuccessLongPass();
        record.crossPass = recordRequestDto.getCrossPass();
        record.successCrossPass = recordRequestDto.getSuccessCrossPass();
        record.tackle = recordRequestDto.getTackle();
        record.intercept = recordRequestDto.getIntercept();
        record.contention = recordRequestDto.getContention();
        record.successContention = recordRequestDto.getSuccessContention();
        record.turnover = recordRequestDto.getTurnover();
        return record;
    }
}
