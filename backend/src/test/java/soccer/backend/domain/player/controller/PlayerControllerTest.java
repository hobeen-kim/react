package soccer.backend.domain.player.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationContextProvider;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import soccer.backend.domain.record.entity.Main;
import soccer.backend.domain.player.entity.Position;
import soccer.backend.domain.player.dto.PlayerResponseDto;
import soccer.backend.domain.record.dto.RecordResponseDto;
import soccer.backend.domain.player.service.PlayerService;

import java.time.LocalDate;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith({RestDocumentationExtension.class, SpringExtension.class})
@SpringBootTest
class PlayerControllerTest {

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(WebApplicationContext webApplicationContext,
                      RestDocumentationContextProvider restDocumentation) {

        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(documentationConfiguration(restDocumentation)
                        .operationPreprocessors()
                        .withResponseDefaults(prettyPrint())) // Enable pretty printing for JSON responses
                .build();
    }

    @MockBean
    private PlayerService playerService;

    @Test
    void create() throws Exception {

        List<RecordResponseDto> recordResponseDtos1 = Lists.newArrayList(
                new RecordResponseDto(1L, 1L, "player1", "game1", LocalDate.parse("2023-01-01"), Position.GK, 0, 0, Main.MAIN, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
        );

        List<RecordResponseDto> recordResponseDtos2 = Lists.newArrayList(
                new RecordResponseDto(2L, 1L, "player2", "game1", LocalDate.parse("2023-01-01"), Position.RCB, 0, 0, Main.MAIN, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0)
                );

        List<PlayerResponseDto> playerResponseDtos = Lists.newArrayList(
                new PlayerResponseDto(1L, "player1", Position.GK, recordResponseDtos1),
                new PlayerResponseDto(2L, "player2", Position.CB, recordResponseDtos2)
        );

        when(playerService.playerList()).thenReturn(playerResponseDtos);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        String expectedJson = objectMapper.writeValueAsString(playerResponseDtos);


        this.mockMvc.perform(get("/players")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson))
                .andDo(document("player-create",
                        responseFields(
                                fieldWithPath("[].id").description("player Id"),
                                fieldWithPath("[].name").description("player 이름"),
                                fieldWithPath("[].position").description("player 포지션"),
                                fieldWithPath("[].recordResponseDto[].playerId").description("player Id"),
                                fieldWithPath("[].recordResponseDto[].gameId").description("game Id"),
                                fieldWithPath("[].recordResponseDto[].playerName").description("player 이름"),
                                fieldWithPath("[].recordResponseDto[].gameName").description("game 이름"),
                                fieldWithPath("[].recordResponseDto[].createdAt").description("game 날짜"),
                                fieldWithPath("[].recordResponseDto[].gamePosition").description("player 의 game 포지션"),
                                fieldWithPath("[].recordResponseDto[].timeIn").description("player 의 game 교체 시간(in)").optional(),
                                fieldWithPath("[].recordResponseDto[].timeOut").description("player 의 game 교체 시간(out)").optional(),
                                fieldWithPath("[].recordResponseDto[].main").description("player 의 game 선발 여부"),
                                fieldWithPath("[].recordResponseDto[].touch").description("player 의 touch 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].goal").description("player 의 goal 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].assist").description("player 의 assist 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].chanceMaking").description("player 의 chanceMaking 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].shoot").description("player 의 shoot 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].validShoot").description("player 의 validShoot 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].dribble").description("player 의 dribble 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].successDribble").description("player 의 successDribble 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].pass").description("player 의 pass 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].successPass").description("player 의 successPass 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].longPass").description("player 의 longPass 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].successLongPass").description("player 의 successLongPass 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].crossPass").description("player 의 crossPass 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].successCrossPass").description("player 의 successCrossPass 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].tackle").description("player 의 tackle 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].intercept").description("player 의 intercept 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].contention").description("player 의 contention 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].successContention").description("player 의 successContention 기록").optional(),
                                fieldWithPath("[].recordResponseDto[].turnover").description("player 의 turnover 기록").optional()

                        )
                ));
    }


}