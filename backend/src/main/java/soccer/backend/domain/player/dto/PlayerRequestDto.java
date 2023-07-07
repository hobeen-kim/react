package soccer.backend.domain.player.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.auth.entity.Member;
import soccer.backend.domain.player.entity.Player;
import soccer.backend.domain.player.entity.Position;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerRequestDto {

    @Pattern(regexp = "^[a-zA-Z가-힣]{1,100}$", message="1~100글자의 영문자 또는 한글을 입력해주세요.")
    private String name;
    @NotNull(message="포지션을 확인해주세요.")
    private Position position;

    public Player toPlayer(Member member) {
        Player player = new Player(name, position);
        player.setMember(member);

        return player;
    }
}
