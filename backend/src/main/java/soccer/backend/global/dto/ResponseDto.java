package soccer.backend.global.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseDto<T> {

    private T data;
    private String status;
    private int code;
    private String message;

    public ResponseDto(T data){
        this.data = data;
    }

    public static <T> ResponseDto<T> of(T data) {
        return new ResponseDto<>(data);
    }

    public static <T> ResponseDto<T> ok(T data) {
        return new ResponseDto<>(data, HttpStatus.OK.name(), HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }
}
