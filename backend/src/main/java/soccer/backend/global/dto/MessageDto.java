package soccer.backend.global.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageDto {

    private String message;

    public MessageDto(String message) {
        this.message = message;
    }
}
