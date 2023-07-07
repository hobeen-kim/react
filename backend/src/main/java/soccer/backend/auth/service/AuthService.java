package soccer.backend.auth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import soccer.backend.auth.dto.MemberRequestDto;
import soccer.backend.auth.dto.MemberResponseDto;
import soccer.backend.auth.dto.TokenDto;
import soccer.backend.auth.jwt.TokenProvider;
import soccer.backend.auth.entity.Member;
import soccer.backend.global.exception.BusinessException;
import soccer.backend.global.exception.ExceptionCode;
import soccer.backend.auth.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AuthService {
    private final AuthenticationManagerBuilder managerBuilder;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenProvider tokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    public MemberResponseDto signup(MemberRequestDto requestDto) {
        if (memberRepository.existsByMemberId(requestDto.getMemberId())) {
            throw new BusinessException(ExceptionCode.MEMBER_DUPLICATED);
        }

        Member member = requestDto.toMember(passwordEncoder);
        return MemberResponseDto.of(memberRepository.save(member));
    }

    public TokenDto login(MemberRequestDto requestDto) {
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();
        TokenDto tokenDto = null;

        // 비밀번호 일치 여부 확인
        try{
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(requestDto.getMemberId());
            if (!passwordEncoder.matches(requestDto.getPassword(), userDetails.getPassword())) {
                throw new BusinessException(ExceptionCode.PASSWORD_NOT_MATCHED);
            }
            Authentication authentication = managerBuilder.getObject().authenticate(authenticationToken);
            tokenDto = tokenProvider.generateTokenDto(authentication);

        }catch (UsernameNotFoundException e) {
            throw new BusinessException(ExceptionCode.MEMBER_NOT_FOUND);
        }

        return tokenDto;
    }

    public TokenDto refreshToken(String refreshToken) {

        if (StringUtils.hasText(refreshToken) && tokenProvider.validateToken(refreshToken)) {
            Authentication authentication = tokenProvider.getAuthenticationFromRefreshToken(refreshToken);
            TokenDto newToken = tokenProvider.generateTokenDto(authentication);
            // Return the new access token in the response
            return newToken;
        } else {
            throw new BusinessException(ExceptionCode.RELOGIN_REQUIRED);
        }
    }

    public String getMemberId(String accessToken) {
        return tokenProvider.getMemberIdFromExpiredToken(accessToken);
    }
}
