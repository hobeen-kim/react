package soccer.backend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soccer.backend.dto.dev.LogLevelDto;

@RestController
@Slf4j
@RequestMapping("/dev")
public class DevController {

    @Value("${logging.level.soccer.backend}")
    private String serviceLoggingLevel;
    @Value("${logging.level.org.springframework.security}")
    private String securityLoggingLevel;
    @Value("${logging.level.org.hibernate.SQL}")
    private String hibernateLoggingLevel;

    @GetMapping("/log")
    public LogLevelDto changeLogging() {
        LogLevelDto logLevelDto = new LogLevelDto(serviceLoggingLevel, securityLoggingLevel, hibernateLoggingLevel);
        return logLevelDto;
    }

}
