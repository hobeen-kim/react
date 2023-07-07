package soccer.backend.domain.dotrecord.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.backend.domain.game.entity.Game;
import soccer.backend.domain.player.entity.Position;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class DotRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    //dotrecord 게임
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "game_id")
    private Game game;

    public void setGame(Game game) {
        this.game = game;
        game.addDotRecord(this);
    }

    //패스를 받은 사람 id
    private Long playerId;
    //패스를 받은 사람 이름
    private String playerName;
    //패스를 받은 사람의 포지션
    private Position gamePosition;
    //패스를 받은 x,y 좌표 값
    private Float x;
    private Float y;

    //슛을 했는지
    private boolean shoot;

    //슛이 유효 슛인지
    private boolean validShoot;

    //슛 끝나는 지점
    private Float shootX;
    private Float shootY;

    //언제인지
    private Integer gameTime;
}
