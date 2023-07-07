package soccer.backend.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

@Getter
public class ErrorResponse {

    private int status;
    private String message;

    private ErrorResponse(ExceptionCode exceptionCode) {
        this.status = exceptionCode.getStatus();
        this.message = exceptionCode.getMessage();
    }

    private ErrorResponse(Exception exception, HttpStatus httpStatus) {
        this.status = httpStatus.value();
        this.message = exception.getMessage();
    }

    public static ErrorResponse of(BusinessException businessException) {
        return new ErrorResponse(businessException.getExceptionCode());
    }

    public static ErrorResponse of(Exception exception, HttpStatus httpStatus) {
        return new ErrorResponse(exception, httpStatus);
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

}
