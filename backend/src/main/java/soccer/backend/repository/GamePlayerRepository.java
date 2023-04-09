package soccer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.domain.info.GamePlayer;

import java.util.List;

public interface GamePlayerRepository extends JpaRepository<GamePlayer, Long> {

    List<GamePlayer> findAllByPlayerId(Long playerId);

    GamePlayer findByGameIdAndPlayerId(Long gameId, Long playerId);

    void deleteByGameIdAndPlayerId(Long gameId, Long playerId);

    void deleteByGameId(Long gameId);
}
