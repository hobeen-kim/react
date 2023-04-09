package soccer.backend.domain.info;

import jakarta.persistence.*;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.player.Player;

@Entity
@Table(name="game_player")
public class GamePlayer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "game_id")
    private Game game;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "player_id")
    private Player player;
}
