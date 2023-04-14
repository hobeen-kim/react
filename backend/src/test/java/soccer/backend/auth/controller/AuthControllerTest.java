package soccer.backend.auth.controller;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import soccer.backend.auth.dto.MemberRequestDto;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@Slf4j
@SpringBootTest
class TestControllerTest {

    @Autowired
    AuthController authController;

    @Autowired
    private Validator validator;

    @Test
    void testValidation() {
        MemberRequestDto requestDto = new MemberRequestDto();
        requestDto.setEmail("test");
        requestDto.setPassword("test");
        requestDto.setMemberId("test");
        requestDto.setName("test");
        requestDto.setNickname("test");

        assertThatThrownBy(() -> authController.signup(requestDto))
                .isInstanceOf(Exception.class)
                .satisfies(e -> log.error("Error occurred: {}", e.getClass().getSimpleName()));

        Set<ConstraintViolation<MemberRequestDto>> violations = validator.validate(requestDto);

        log.info("violations: {}", violations);

        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("이메일 형식이 아닙니다.");

        requestDto.setEmail("test@test.test");

        violations = validator.validate(requestDto);
        assertThat(violations).isEmpty();

        ResponseEntity<?> responseEntity = authController.signup(requestDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(200));
    }
}
