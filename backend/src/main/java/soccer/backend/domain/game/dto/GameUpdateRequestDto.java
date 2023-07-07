package soccer.backend.domain.game.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import soccer.backend.global.annotation.MinMax999;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GameUpdateRequestDto {

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

}
