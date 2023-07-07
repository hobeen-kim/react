package soccer.backend.domain.player.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.backend.domain.player.dto.PlayerRequestDto;
import soccer.backend.global.dto.MessageDto;
import soccer.backend.domain.player.dto.PlayerResponseDto;
import soccer.backend.domain.player.service.PlayerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/players")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping
    public ResponseEntity<List<PlayerResponseDto>> playerList() {

        return ResponseEntity.ok(playerService.playerList());
    }

    @PostMapping
    public ResponseEntity<PlayerResponseDto> createPlayer(@RequestBody @Valid PlayerRequestDto playerRequestDto)  {

        return ResponseEntity.ok(playerService.createPlayer(playerRequestDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageDto> updatePlayer(
            @RequestBody @Valid PlayerRequestDto playerRequestDto,
            @PathVariable("id") Long id
    )  {

        return ResponseEntity.ok(playerService.updatePlayer(id, playerRequestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageDto> deletePlayer(@PathVariable("id") Long id) {

        return ResponseEntity.ok(playerService.deletePlayer(id));
    }
}
