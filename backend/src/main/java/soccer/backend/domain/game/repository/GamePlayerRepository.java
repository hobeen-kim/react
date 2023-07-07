package soccer.backend.domain.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.domain.game.entity.GamePlayer;

import java.util.List;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {

    List<GamePlayer> findAllByPlayerId(Long playerId);

    GamePlayer findByGameIdAndPlayerId(Long gameId, Long playerId);

    void deleteByGameIdAndPlayerId(Long gameId, Long playerId);

    void deleteByGameId(Long gameId);
}
