package soccer.backend.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import soccer.backend.domain.member.Authority;
import soccer.backend.domain.member.Member;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Slf4j
public class MemberRequestDto {

    @Pattern(regexp = "^[a-z]+\\d*$", message = "4~20글자의 소문자, 숫자로 구성해주세요.")
    @Size(min = 4, max = 20, message = "4~20글자의 소문자, 숫자로 구성해주세요.")
    private String memberId;
    @Email(message="이메일 형식을 확인해주세요.")
    private String email;
    @NotBlank(message = "PassWord 를 입력해주세요.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])(?=.*[!@#$%^&*()_+=|`-])(?=\\S+$).{8,30}$", message = "8~30글자의 소문자, 숫자, 특수문자로 구성해주세요.")
    private String password;
    @Pattern(regexp = "^[a-zA-Z가-힣]{1,100}$")
    private String name;
    @Pattern(regexp = "^[a-zA-Z가-힣][a-zA-Z가-힣0-9]{1,20}$")
    private String nickname;

    public Member toMember(PasswordEncoder passwordEncoder) {
        return Member.builder()
                .memberId(memberId)
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .nickname(nickname)
                .authority(Authority.ROLE_USER)
                .build();
    }

    public UsernamePasswordAuthenticationToken toAuthentication() {
        return new UsernamePasswordAuthenticationToken(memberId, password);
    }
}
