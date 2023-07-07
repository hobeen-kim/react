package soccer.backend.domain.dotrecord.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.domain.game.entity.Game;
import soccer.backend.domain.dotrecord.entity.DotRecord;

import java.util.List;

public interface DotRecordRepository extends JpaRepository<DotRecord, Long> {

    void removeByGame(Game game);

    void deleteByGameId(Long gameId);

    List<DotRecord> findByGame(Game game);
}
