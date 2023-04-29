package soccer.backend.auth.controller;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import soccer.backend.auth.dto.MemberRequestDto;
import soccer.backend.auth.dto.MemberResponseDto;
import soccer.backend.auth.dto.TokenDto;
import soccer.backend.auth.service.AuthService;
import org.springframework.http.ResponseCookie;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final RedisTemplate<String, String> redisTemplate;
    @Value("${cookie.secure}")
    private boolean secure;
    @Value("${cookie.domain}")
    private String domain;

    @PostMapping("/signup")
    public ResponseEntity<MemberResponseDto> signup(@RequestBody @Valid MemberRequestDto requestDto) {

        MemberResponseDto responseDto = authService.signup(requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(
            @RequestBody MemberRequestDto requestDto, HttpServletResponse response
    ) {
        TokenDto token = authService.login(requestDto);

        //create Cookie and set it in response
        createCookie(response, token);

        //store refresh token in redis
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        vop.set(requestDto.getMemberId(), token.getRefreshToken());

        token.setRefreshToken(null);

        return ResponseEntity.ok(token);
    }

    @GetMapping("/refreshToken")
    public ResponseEntity<?> refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        String accessToken = request.getHeader("X-Expired-Access-Token").substring(7);
        String memberId = authService.getMemberId(accessToken);
        String refreshToken = null;
        ValueOperations<String, String> vop = redisTemplate.opsForValue();

        if (request.getCookies() != null) {

            for (Cookie cookie : request.getCookies()) {
                if ("refreshToken".equals(cookie.getName())) {
                    refreshToken = cookie.getValue();
                    break;
                }
            }
        }
        //refreshToken 이 없을 때
        if (refreshToken == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("please retry login");
        }
        //accessToken 의 memberId 로 얻은 저장된 refreshToken 값과 받은 refreshToken 값이 다를 때
        if(!refreshToken.equals(vop.get(memberId))){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("please retry login");
        }

        //issue Token (accessToken, refreshToken)
        TokenDto token = authService.refreshToken(refreshToken);

        //store refreshToken in cookie
        createCookie(response, token);

        //store refresh token in redis
        vop.set(memberId, token.getRefreshToken());

        //remove refreshToken from response
        //because it is stored in cookie
        token.setRefreshToken(null);

        return ResponseEntity.ok(token);
    }

    private void createCookie(HttpServletResponse response, TokenDto token) {
        ResponseCookie cookie = ResponseCookie.from("refreshToken", token.getRefreshToken())
                .domain(domain)
                .path("/")
                .httpOnly(true)
                .secure(secure)
                .sameSite("Strict")
                .maxAge(60 * 60 * 24 * 14)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

}
