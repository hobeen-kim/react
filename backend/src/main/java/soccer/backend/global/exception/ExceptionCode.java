package soccer.backend.global.exception;

import lombok.Getter;

public enum ExceptionCode {

    MEMBER_NOT_FOUND(401, "Member Not Found"),
    //비밀번호가 일치하지 않음
    PASSWORD_NOT_MATCHED(401, "Password Not Matched"),
    //재 로그인 요구
    RELOGIN_REQUIRED(401, "Login Required"),
    MEMBER_DUPLICATED(404, "Team Not Found"),
    TOKEN_NOT_VALID(401, "Token Not Valid"),
    TOKEN_EXPIRED(401, "Token Expired"),
    POST_NOT_FOUND(404, "Post Not Found"),
    //권한이 없음
    ACCESS_DENIED(403, "Access Denied"),
    //존재하지 않는 댓글
    COMMENT_NOT_FOUND(404, "Comment Not Found");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }


}
