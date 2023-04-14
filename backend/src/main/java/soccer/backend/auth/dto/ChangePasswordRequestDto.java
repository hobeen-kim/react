package soccer.backend.auth.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordRequestDto {
    private String memberId;
    private String exPassword;
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+=|`-])(?=\\S+$).{8,30}$", message = "8~30글자의 소문자, 숫자, 특수문자로 구성해주세요.")
    private String newPassword;
}
