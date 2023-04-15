package soccer.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.backend.config.SecurityUtil;
import soccer.backend.domain.info.GamePlayer;
import soccer.backend.domain.info.Record;
import soccer.backend.domain.member.Member;
import soccer.backend.domain.player.Player;
import soccer.backend.dto.player.PlayerRequestDto;
import soccer.backend.dto.MessageDto;
import soccer.backend.dto.player.PlayerResponseDto;
import soccer.backend.dto.record.RecordResponseDto;
import soccer.backend.repository.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class PlayerService{

    private final PlayerRepository playerRepository;
    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;
    private final GamePlayerRepository gamePlayerRepository;


    //선수 리스트 조회
    public List<PlayerResponseDto> playerList() {

        Member member = getMember();
        List<Player> players = playerRepository.findByMember(member);

        List<PlayerResponseDto> playerList = new ArrayList<>();

        for (Player player : players) {
            PlayerResponseDto playerResponseDto = PlayerResponseDto.of(player, playerRecord(player));
            playerList.add(playerResponseDto);
        }

        return playerList;
    }

    //선수 기록 조회
    public List<RecordResponseDto> playerRecord(Player player) {
        List<Record> records = recordRepository.findByPlayer(player);

        List<RecordResponseDto> recordList = new ArrayList<>();

        for (Record record : records) {
            RecordResponseDto recordResponseDto = new RecordResponseDto(record);
            recordList.add(recordResponseDto);
        }

        return recordList;
    }

    //선수 생성
    public PlayerResponseDto createPlayer(PlayerRequestDto playerRequestDto) {

        Member member = getMember();
        Player player = playerRequestDto.toPlayer(member);
        playerRepository.save(player);

        return PlayerResponseDto.of(player, playerRecord(player));
    }


    //선수 수정
    public MessageDto updatePlayer(PlayerRequestDto playerRequestDto) {

        Player player = playerRepository.findById(playerRequestDto.getId()).orElseThrow(
                ()-> new IllegalArgumentException("선수가 존재하지 않습니다."));

        //인증 정보로 회원의 player 인지 확인하고 수정
        if(isPlayer(player)){
            player.setName(playerRequestDto.getName());
            player.setPosition(playerRequestDto.getPosition());
            return new MessageDto(player.getName() + " 선수 수정 완료");
        } else {
            throw new RuntimeException("선수 수정 권한이 없습니다.");
        }

    }

    //선수 삭제
    public MessageDto deletePlayer(Long id) {

        Member member = getMember();
        Player player = playerRepository.findById(id).orElseThrow();
        String name = player.getName();

        if(!isPlayer(player)){
            throw new RuntimeException("선수 삭제 권한이 없습니다.");
        }
        // game_player 테이블에서 player_id를 참조하는 레코드를 모두 삭제
        List<GamePlayer> gamePlayers = gamePlayerRepository.findAllByPlayerId(id);
        gamePlayerRepository.deleteAll(gamePlayers);

        // Player 엔티티 삭제
        playerRepository.deleteById(id);

        return new MessageDto(name + " 선수 삭제 완료");
    }

    //플레이어 1명 조회
    public Player player(Long id) {


        Player player = playerRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("선수가 존재하지 않습니다."));

        if(!isPlayer(player)){
            throw new RuntimeException("선수 조회 권한이 없습니다.");
        }
        return player;
    }

    //인증 정보로 회원 조회
    private Member getMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return memberRepository.findByMemberId(authentication.getName()).orElseThrow();
    }

    //인증 정보로 회원의 player 여부 조회
    public boolean isPlayer(Player player) {
        Member member = getMember();
        List<Player> players = playerRepository.findByMember(member);
        Player requestPlayer = playerRepository.findById(player.getId()).orElseThrow();
        boolean isPlayer = players.contains(requestPlayer);
        return isPlayer;
    }

}
