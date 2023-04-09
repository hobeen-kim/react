package soccer.backend.dto.player;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.domain.member.Member;
import soccer.backend.domain.player.Player;
import soccer.backend.domain.player.Position;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequestDto {

    private Long id;
    private String name;
    private Position position;

    public Player toPlayer(Member member) {
        Player player = new Player(name, position);
        player.setMember(member);

        return player;
    }
}
