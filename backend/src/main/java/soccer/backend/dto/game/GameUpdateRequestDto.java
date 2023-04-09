package soccer.backend.dto.game;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameUpdateRequestDto {

    private Long id;
    private String gameName;
    private String opponent;
    private String location;
    private Integer gf;
    private Integer ga;
    private LocalDate createdAt;

}
