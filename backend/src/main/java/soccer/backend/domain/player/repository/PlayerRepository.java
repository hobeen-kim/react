package soccer.backend.domain.player.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.domain.game.entity.Game;
import soccer.backend.auth.entity.Member;
import soccer.backend.domain.player.entity.Player;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {

    List<Player> findByMember(Member member);

    List<Player> findByGames(Game game);

    List<Player> findByMemberAndGames(Member member, Game game);
}