package soccer.backend.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import soccer.backend.auth.entity.Authority;
import soccer.backend.auth.entity.Member;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberResponseDto {
    private String email;
    private String memberId;
    private String name;
    private String nickname;
    private Authority authority;

    public static MemberResponseDto of(Member member) {
        return MemberResponseDto.builder()
                .email(member.getEmail())
                .memberId(member.getMemberId())
                .name(member.getName())
                .nickname(member.getNickname())
                .authority(member.getAuthority())
                .build();
    }
}
