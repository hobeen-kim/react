package soccer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.info.Record;
import soccer.backend.domain.player.Player;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findByPlayerIn(List<Player> players);

    List<Record> findByPlayer(Player player);

    void deleteByPlayerId(Long playerId);

    List<Record> findByGame(Game game);

    Optional<Record> findByGameAndPlayer(Game game, Player player);

    void deleteByGameAndPlayer(Game game, Player player);

    void deleteByGameId(Long gameId);
}