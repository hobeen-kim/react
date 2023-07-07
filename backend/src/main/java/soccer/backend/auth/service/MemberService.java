package soccer.backend.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.backend.auth.dto.MemberResponseDto;
import soccer.backend.config.SecurityUtil;
import soccer.backend.auth.entity.Member;
import soccer.backend.global.exception.BusinessException;
import soccer.backend.global.exception.ExceptionCode;
import soccer.backend.auth.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResponseDto getMyInfoBySecurity() {
        return memberRepository.findById(SecurityUtil.getCurrentMemberId())
                .map(MemberResponseDto::of)
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
    }

    @Transactional
    public MemberResponseDto changeMemberNickname(String memberId, String nickname) {
        Member member = memberRepository.findByMemberId(memberId).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        member.setNickname(nickname);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    @Transactional
    public MemberResponseDto changeMemberPassword(String memberId, String exPassword, String newPassword) {
        Member member = memberRepository.findById(SecurityUtil.getCurrentMemberId()).orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다"));
        if (!passwordEncoder.matches(exPassword, member.getPassword())) {
            throw new RuntimeException("비밀번호가 맞지 않습니다");
        }
        member.setPassword(passwordEncoder.encode((newPassword)));
        return MemberResponseDto.of(memberRepository.save(member));
    }

    /**
     * 현재 로그인된 사용자
     * @return : 현재 로그인된 사용자
     */
    public Member getCurrentMember(){
        return memberRepository.findById(getCurrentMemberId()).orElseThrow(
                () -> new BusinessException(ExceptionCode.MEMBER_NOT_FOUND)
        );
    }

    /**
     * 현재 로그인된 사용자 id
     * @return : 현재 로그인된 사용자 id
     */
    public Long getCurrentMemberId(){
        return SecurityUtil.getCurrentMemberId();
    }
}