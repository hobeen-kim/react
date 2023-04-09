package soccer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.member.Member;

import java.util.List;
import java.util.Optional;

public interface GameRepository extends JpaRepository<Game, Long> {

    List<Game> findByMember(Member member);

    void deleteById(Long gameId);
}