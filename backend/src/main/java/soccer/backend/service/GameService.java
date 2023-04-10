package soccer.backend.service;

import jakarta.persistence.NonUniqueResultException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soccer.backend.domain.game.Game;
import soccer.backend.domain.info.DotRecord;
import soccer.backend.domain.info.Record;
import soccer.backend.domain.member.Member;
import soccer.backend.domain.player.Player;
import soccer.backend.dto.MessageDto;
import soccer.backend.dto.dotRecord.DotRecordRequestDto;
import soccer.backend.dto.dotRecord.DotRecordResponseDto;
import soccer.backend.dto.game.*;
import soccer.backend.dto.game.gameField.GameFieldRequestDto;
import soccer.backend.dto.game.gameField.GameFieldResponseDto;
import soccer.backend.dto.record.RecordRequestDto;
import soccer.backend.dto.record.RecordResponseDto;
import soccer.backend.repository.*;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class GameService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;
    private final MemberRepository memberRepository;
    private final RecordRepository recordRepository;
    private final GamePlayerRepository gamePlayerRepository;
    private final DotRecordRepository dotRecordRepository;


    //게임 목록 전체 조회
    public List<GameListResponseDto> gameList() {

        Member member = getMember();
        List<Player> players = playerRepository.findByMember(member);
        List<Game> games = gameRepository.findByMember(member);
        List<GameListResponseDto> gameList = new ArrayList<>();

        for (Game game : games) {
            GameListResponseDto gameListResponseDto = GameListResponseDto.of(game, players, gamePlayer(game));
            gameList.add(gameListResponseDto);
        }

        return gameList;
    }

    //게임 1개에 대한 디테일 반환
    public GameListResponseDto gameDetail(Long gameId) {

        Member member = getMember();
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 경기가 없습니다.")
        );
        List<Player> players = playerRepository.findByMember(member);
        if(isGame(game)){
            return GameListResponseDto.of(game, players, gamePlayer(game));
        }
        return null;
    }

    //경기에 참가한 선수와 그 기록을 조회해서 반환
    public List<GamePlayerResponseDto> gamePlayer(Game game) {
        List<Player> players = playerRepository.findByGames(game);
        List<GamePlayerResponseDto> playerList = new ArrayList<>();

        for (Player player : players) {
            Record record = recordRepository.findByGameAndPlayer(game, player).orElseThrow(
                    () -> new IllegalArgumentException("해당 경기에 참가한 선수가 아닙니다.")
            );
            RecordResponseDto recordResponseDto = new RecordResponseDto(record);
            GamePlayerResponseDto gamePlayerResponseDto = GamePlayerResponseDto.of(player, recordResponseDto);
            playerList.add(gamePlayerResponseDto);
        }

        return playerList;
    }

    //게임 생성 시 최초 선수데이터 반환 (리스트 형태)
    public List<GamePlayerResponseDto> gameCreateInit() {

        Member member = getMember();
        List<Player> players = playerRepository.findByMember(member);
        List<GamePlayerResponseDto> playerList = new ArrayList<>();
        for (Player player : players) {
            GamePlayerResponseDto gamePlayerResponseDto = GamePlayerResponseDto.of(player, null);
            playerList.add(gamePlayerResponseDto);
        }

        return playerList;
    }

    //게임 생성
    public GameListResponseDto gameCreate(GameCreateRequestDto request) {

        Member member = getMember();
        Game game = GameCreateRequestDto.toGame(request);
        game.setMember(member);
        gameRepository.save(game);
        request.getGamePlayerAddRequestDto().forEach(playerRequestDto -> {
            Player player = playerRepository.findById(playerRequestDto.getPlayerId()).orElseThrow(
                    () -> new IllegalArgumentException("해당 선수가 존재하지 않습니다.")
            );
            game.addPlayer(player);
            player.addGame(game);
            Record record = new Record(game, player, playerRequestDto.getGamePosition(), playerRequestDto.getMain());
            record.setTimeIn(playerRequestDto.getTimeIn());
            record.setTimeOut(playerRequestDto.getTimeOut());
            recordRepository.save(record);
        });

        return GameListResponseDto.of(game, playerRepository.findByMember(member), gamePlayer(game));
    }

    //게임 정보 업데이트(경기명, 상대팀, 경기장, 득점, 실점, 경기일)
    public GameListResponseDto gameUpdate(GameUpdateRequestDto request) {

        Member member = getMember();
        List<Player> players = playerRepository.findByMember(member);

        Game game = gameRepository.findById(request.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 경기가 존재하지 않습니다.")
        );

        if(isGame(game)){
            game.setGameName(request.getGameName());
            game.setOpponent(request.getOpponent());
            game.setLocation(request.getLocation());
            game.setGF(request.getGf());
            game.setGA(request.getGa());
            game.setCreatedAt(request.getCreatedAt());

           return GameListResponseDto.of(game, players, gamePlayer(game));
        }
        return null;
    }

    //게임 삭제
    public MessageDto gameDelete(Long gameId) {

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 경기가 존재하지 않습니다.")
        );

        if(isGame(game)){
            // game_player 테이블에서 player_id를 참조하는 레코드를 모두 삭제
//            gamePlayerRepository.deleteByGameId(gameId);
            // record 테이블에서 game_id를 참조하는 레코드를 모두 삭제
            recordRepository.deleteByGameId(gameId);
            // dotRecord 테이블에서 game_id를 참조하는 레코드를 모두 삭제
            dotRecordRepository.deleteByGameId(gameId);
            // game 테이블에서 해당 레코드 삭제
            gameRepository.delete(game);

            return new MessageDto("삭제되었습니다.");
        }
        return new MessageDto("삭제에 실패했습니다.");
    }

    //게임 내에 선수를 추가
    public GameListResponseDto addPlayer(Long gameId, GamePlayerAddRequestDto request) {

        Member member = getMember();
        List<Player> players = playerRepository.findByMember(member);

        Player player = playerRepository.findById(request.getPlayerId()).orElseThrow(
                () -> new IllegalArgumentException("해당 선수가 존재하지 않습니다.")
        );
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 경기가 존재하지 않습니다.")
        );

        if(isGame(game) && isPlayer(player)){
            try{
                player.addGame(game);
                game.addPlayer(player);
                Record record = new Record(game, player, request.getGamePosition(), request.getMain());
                recordRepository.save(record);
                return GameListResponseDto.of(game, players, gamePlayer(game));
            }catch (IncorrectResultSizeDataAccessException e) {
                throw new RuntimeException("이미 해당 경기에 참가한 선수입니다.");
            }
        }
        return null;
    }

    //게임 내 선수의 기록을 업데이트
    public RecordResponseDto updatePlayer(Long gameId, RecordRequestDto request) {

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 경기가 존재하지 않습니다.")
        );
        Player player = playerRepository.findById(request.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 선수가 존재하지 않습니다.")
        );
        Record record = recordRepository.findByGameAndPlayer(game, player).orElseThrow(
                () -> new IllegalArgumentException("해당 경기에 참가한 선수가 아닙니다.")
        );

        if(isGame(game) && isPlayer(player)){

            Record.updateRecord(record, request);
            return new RecordResponseDto(record);
        }
        return null;
    }

    //게임 내 선수를 삭제
    @Transactional
    public GameListResponseDto deletePlayer(Long gameId, Long playerId) {
        Member member = getMember();
        List<Player> players = playerRepository.findByMember(member);
        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 경기가 존재하지 않습니다.")
        );
        Player player = playerRepository.findById(playerId).orElseThrow(
                () -> new IllegalArgumentException("해당 선수가 존재하지 않습니다.")
        );
        if(isGame(game) && isPlayer(player)){
            // game_player 테이블에서 player_id를 참조하는 레코드를 모두 삭제
            gamePlayerRepository.deleteByGameIdAndPlayerId(gameId, playerId);

            //record 테이블에서 player_id를 참조하는 레코드를 모두 삭제
            Record record = recordRepository.findByGameAndPlayer(game, player).orElseThrow(
                    () -> new IllegalArgumentException("해당 경기에 참가한 선수가 아닙니다.")
            );
            recordRepository.delete(record);

            return GameListResponseDto.of(game, players, gamePlayer(game));
        }
        return null;
    }

    //게임 내 DotRecord 가 있는 gameField 를 조회
    //현재 사용하지 않는 중
    public GameFieldResponseDto getGameField(Long gameId) {

        Member member = getMember();

        Game game = gameRepository.findById(gameId).orElseThrow(
                () -> new IllegalArgumentException("해당 경기가 존재하지 않습니다.")
        );
        List<Player> players = playerRepository.findByMemberAndGames(member, game);
        if(!isGame(game)){
            return null;
        }
        List<DotRecord> dotRecords = dotRecordRepository.findByGame(game);
        List<DotRecordResponseDto> dotRecordResponseDto = DotRecordResponseDto.toDotRecordResponseDtoList(dotRecords);

        return GameFieldResponseDto.of(game, players, dotRecordResponseDto);
    }

    //게임 내 DotRecord 를 추가
    public GameFieldResponseDto createGameField(GameFieldRequestDto request) {

        Member member = getMember();

        Game game = gameRepository.findById(request.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 경기가 존재하지 않습니다.")
        );
        List<Player> players = playerRepository.findByMemberAndGames(member, game);

        if(!isGame(game)){
            return null;
        }
        dotRecordRepository.removeByGame(game);
        List<DotRecord> dotRecords = toDotRecordList(game, request.getDotRecordRequestDto());
        List<DotRecordResponseDto> dotRecordResponseDto = DotRecordResponseDto.toDotRecordResponseDtoList(dotRecords);

        return GameFieldResponseDto.of(game, players, dotRecordResponseDto);
    }

    //게임 내 DotRecord 를 추가하기 위해서 List<DotRecord> 로 변환
    //createGameField 메서드에서 사용
    public List<DotRecord> toDotRecordList(Game game, DotRecordRequestDto[] dotRecordRequestDto) {

        List<DotRecord> dotRecords = new ArrayList<>();
        for (DotRecordRequestDto request : dotRecordRequestDto) {
            DotRecord dotRecord = new DotRecord();
            dotRecord.setGame(game);
            dotRecord.setPlayerId(request.getPlayerId());
            dotRecord.setPlayerName(request.getPlayerName());
            dotRecord.setGamePosition(request.getGamePosition());
            dotRecord.setX(request.getX());
            dotRecord.setY(request.getY());
            dotRecord.setShoot(request.isShoot());
            dotRecord.setValidShoot(request.isValidShoot());
            dotRecord.setShootX(request.getShootX());
            dotRecord.setShootY(request.getShootY());
            dotRecord.setGameTime(request.getGameTime());
            dotRecordRepository.save(dotRecord);
        }
        return dotRecords;
    }


    //인증 정보로 회원 조회
    private Member getMember() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return memberRepository.findById(Long.parseLong(authentication.getName())).orElseThrow();
    }

    //인증 정보로 회원의 game 여부 조회
    public boolean isGame(Game game) {
        Member member = getMember();
        List<Game> games = gameRepository.findByMember(member);
        Game requestGame = gameRepository.findById(game.getId()).orElseThrow();
        boolean isGame = games.contains(requestGame);
        return isGame;
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
