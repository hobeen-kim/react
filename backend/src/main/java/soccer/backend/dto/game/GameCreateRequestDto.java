package soccer.backend.dto.game;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.backend.annotation.MinMax999;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.member.Member;
import soccer.backend.dto.player.PlayerRequestDto;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameCreateRequestDto {

    @Size(min=1, max=100, message="경기명은 1~100 글자 사이로 작성해주세요.")
    private String gameName;
    @Size(max=100, message="상대팀은 100 글자 이하로 작성해주세요.")
    private String opponent;
    @Size(max=100, message="위치는 100 글자 이하로 작성해주세요.")
    private String location;
    @MinMax999
    private int gf;
    @MinMax999
    private int ga;
    @NotNull(message="날짜를 확인해주세요.")
    private LocalDate createdAt;
    private List<GamePlayerAddRequestDto> gamePlayerAddRequestDto;

    public static Game toGame(GameCreateRequestDto request) {
        Game game = new Game();
        game.setGameName(request.getGameName());
        game.setOpponent(request.getOpponent());
        game.setLocation(request.getLocation());
        game.setGF(request.getGf());
        game.setGA(request.getGa());
        game.setCreatedAt(request.getCreatedAt());

        return game;
    }
}
