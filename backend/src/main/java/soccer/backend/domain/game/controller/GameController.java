package soccer.backend.domain.game.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.backend.domain.game.dto.GameCreateRequestDto;
import soccer.backend.domain.game.dto.GameListResponseDto;
import soccer.backend.domain.game.dto.GamePlayerAddRequestDto;
import soccer.backend.domain.game.dto.GameUpdateRequestDto;
import soccer.backend.global.dto.MessageDto;
import soccer.backend.domain.game.dto.gameField.GameFieldRequestDto;
import soccer.backend.domain.game.dto.gameField.GameFieldResponseDto;
import soccer.backend.domain.record.dto.RecordRequestDto;
import soccer.backend.domain.record.dto.RecordResponseDto;
import soccer.backend.domain.game.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/games")
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final GameService gameService;

    @GetMapping
    public ResponseEntity<List<GameListResponseDto>> gameList() {
        return ResponseEntity.ok(gameService.gameList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameListResponseDto> gameDetail(@PathVariable("id") Long gameId) {
        return ResponseEntity.ok(gameService.gameDetail(gameId));
    }

    @PostMapping
    public ResponseEntity<GameListResponseDto> gameCreate(@RequestBody @Valid GameCreateRequestDto request) {
        GameListResponseDto gameListResponseDto = gameService.gameCreate(request);
        return ResponseEntity.ok(gameListResponseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameListResponseDto> gameUpdate(@PathVariable("id") Long gameId, @RequestBody @Valid GameUpdateRequestDto request) {
        return ResponseEntity.ok(gameService.gameUpdate(gameId, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> gameDelete(@PathVariable("id") Long gameId) {
        return ResponseEntity.ok(gameService.gameDelete(gameId));
    }

    @PostMapping("/{id}/players")
    public ResponseEntity<GameListResponseDto> addPlayer(
            @RequestBody @Valid GamePlayerAddRequestDto request,
            @PathVariable("id") Long gameId) {
        return ResponseEntity.ok(gameService.addPlayer(gameId, request));
    }

    @PutMapping("/{id}/players/{playerId}")
    public ResponseEntity<RecordResponseDto> updatePlayer(
            @RequestBody @Valid RecordRequestDto request,
            @PathVariable("id") Long id,
            @PathVariable("playerId") Long playerId) {
        return ResponseEntity.ok(gameService.updatePlayer(id, playerId, request));
    }

    @DeleteMapping("/{id}/players/{playerId}")
    public ResponseEntity<GameListResponseDto> deletePlayer(
            @PathVariable("id") Long id,
            @PathVariable("playerId") Long playerId) {
        return ResponseEntity.ok(gameService.deletePlayer(id, playerId));
    }

    @GetMapping("/{id}/gameField")
    public ResponseEntity<GameFieldResponseDto> gameField(@PathVariable("id") Long id) {
        return ResponseEntity.ok(gameService.getGameField(id));
    }

    @PostMapping("/{id}/gameField")
    public ResponseEntity<GameFieldResponseDto> createGameField(
            @RequestBody @Valid GameFieldRequestDto request,
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(gameService.createGameField(id, request));
    }
}
