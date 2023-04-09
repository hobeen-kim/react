package soccer.backend.dto.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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

    private String gameName;
    private String opponent;
    private String location;
    private Integer gf;
    private Integer ga;
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
