package soccer.backend.domain.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.domain.game.entity.Game;
import soccer.backend.auth.entity.Member;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByMember(Member member);

    void deleteById(Long gameId);
}