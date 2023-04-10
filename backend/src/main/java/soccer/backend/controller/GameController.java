package soccer.backend.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.backend.dto.MessageDto;
import soccer.backend.dto.game.*;
import soccer.backend.dto.game.gameField.GameFieldRequestDto;
import soccer.backend.dto.game.gameField.GameFieldResponseDto;
import soccer.backend.dto.record.RecordRequestDto;
import soccer.backend.dto.record.RecordResponseDto;
import soccer.backend.service.GameService;

import java.util.List;

@RestController
@RequestMapping("/game")
@RequiredArgsConstructor
@Slf4j
public class GameController {

    private final GameService gameService;

    @GetMapping("/list")
    public ResponseEntity<List<GameListResponseDto>> gameList() {
        return ResponseEntity.ok(gameService.gameList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameListResponseDto> gameDetail(@PathVariable("id") Long gameId) {
        return ResponseEntity.ok(gameService.gameDetail(gameId));
    }

    @GetMapping("/create")
    public ResponseEntity<List<GamePlayerResponseDto>> gameCreate() {
        return ResponseEntity.ok(gameService.gameCreateInit());
    }

    @PostMapping("/create")
    public ResponseEntity<GameListResponseDto> gameCreate(@RequestBody GameCreateRequestDto request) {
        GameListResponseDto gameListResponseDto = gameService.gameCreate(request);
        return ResponseEntity.ok(gameListResponseDto);
    }

    @PutMapping("/update")
    public ResponseEntity<GameListResponseDto> gameUpdate(@RequestBody GameUpdateRequestDto request) {
        return ResponseEntity.ok(gameService.gameUpdate(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageDto> gameDelete(@RequestParam("id") Long gameId) {
        return ResponseEntity.ok(gameService.gameDelete(gameId));
    }

    @PostMapping("/addPlayer")
    public ResponseEntity<GameListResponseDto> addPlayer(
            @RequestBody GamePlayerAddRequestDto request,
            @RequestParam("id") Long gameId) {
        return ResponseEntity.ok(gameService.addPlayer(gameId, request));
    }

    @PutMapping("/updatePlayer")
    public ResponseEntity<RecordResponseDto> updatePlayer(
            @RequestBody RecordRequestDto request,
            @RequestParam("id") Long gameId) {
        return ResponseEntity.ok(gameService.updatePlayer(gameId, request));
    }

    @DeleteMapping("/deletePlayer")
    public ResponseEntity<GameListResponseDto> deletePlayer(
            @RequestParam("id") Long gameId,
            @RequestParam("playerId") Long playerId) {
        return ResponseEntity.ok(gameService.deletePlayer(gameId, playerId));
    }

    @GetMapping("/gameField")
    public ResponseEntity<GameFieldResponseDto> gameField(@RequestParam("id") Long gameId) {
        return ResponseEntity.ok(gameService.getGameField(gameId));
    }

    @PostMapping("/createGameField")
    public ResponseEntity<GameFieldResponseDto> createGameField(@RequestBody GameFieldRequestDto request) {
        return ResponseEntity.ok(gameService.createGameField(request));
    }
}
