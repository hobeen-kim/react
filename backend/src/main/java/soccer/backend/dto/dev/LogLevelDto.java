package soccer.backend.dto.dev;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogLevelDto {

    private String serviceLoggingLevel;
    private String securityLoggingLevel;
    private String hibernateLoggingLevel;

}
