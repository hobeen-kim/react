package soccer.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soccer.backend.dto.player.PlayerRequestDto;
import soccer.backend.dto.MessageDto;
import soccer.backend.dto.player.PlayerResponseDto;
import soccer.backend.service.PlayerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/list")
    public ResponseEntity<List<PlayerResponseDto>> playerList() {


        return ResponseEntity.ok(playerService.playerList());
    }

    @PostMapping("/create")
    public ResponseEntity<PlayerResponseDto> createPlayer(@RequestBody @Valid PlayerRequestDto playerRequestDto)  {

        return ResponseEntity.ok(playerService.createPlayer(playerRequestDto));
    }

    @PutMapping("/update")
    public ResponseEntity<MessageDto> updatePlayer(@RequestBody @Valid PlayerRequestDto playerRequestDto)  {

        return ResponseEntity.ok(playerService.updatePlayer(playerRequestDto));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<MessageDto> deletePlayer(@RequestParam(name="id") Long id) {

        return ResponseEntity.ok(playerService.deletePlayer(id));
    }
}
