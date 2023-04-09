package soccer.backend.auth.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.backend.auth.dto.MemberRequestDto;
import soccer.backend.auth.dto.MemberResponseDto;
import soccer.backend.auth.dto.TokenDto;
import soccer.backend.auth.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        log.info("test");
        return ResponseEntity.ok("test");
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody MemberRequestDto requestDto) {

        try {
            MemberResponseDto responseDto = authService.signup(requestDto);
            return ResponseEntity.ok(responseDto);
        } catch (RuntimeException e) {
            String errorMessage = e.getMessage();
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody MemberRequestDto requestDto) {
        try{
            TokenDto token = authService.login(requestDto);
            return ResponseEntity.ok(token);
        }catch(RuntimeException e){
            String errorMessage = "로그인 정보를 다시 확인해주세요.";
            return ResponseEntity.badRequest().body(errorMessage);
        }
    }
}
