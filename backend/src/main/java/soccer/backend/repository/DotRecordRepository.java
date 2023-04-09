package soccer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.info.DotRecord;

import java.util.List;

public interface DotRecordRepository extends JpaRepository<DotRecord, Long> {

    void removeByGame(Game game);

    void deleteByGameId(Long gameId);

    List<DotRecord> findByGame(Game game);
}
