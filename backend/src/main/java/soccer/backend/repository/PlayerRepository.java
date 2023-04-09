package soccer.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.member.Member;
import soccer.backend.domain.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends JpaRepository<Player, Long>, PlayerRepositoryCustom {

    List<Player> findByMember(Member member);

    List<Player> findByGames(Game game);

    List<Player> findByMemberAndGames(Member member, Game game);
}