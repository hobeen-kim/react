import React, { useState, useEffect, useContext, useRef } from 'react';
import { Table } from 'react-bootstrap';
import { GameContext } from '../../store/game/game-context';
import LoadingSpinner from '../../UI/LoadingSpinner'
import { Link, useNavigate } from 'react-router-dom';
import { Button } from 'react-bootstrap';



const GameList = () => {

const [gameList, setGameList] = useState([]);
const [isLoading, setIsLoading] = useState(false);
const gameCtx = useContext(GameContext);
const navigate = useNavigate();
//포지션 목록을 가져온다.
const positions = gameCtx.positions;

useEffect(() => {
    const fetchGames = async () => {
        setIsLoading(true);
        await gameCtx.getGames();
        setGameList(gameCtx.games);
        setIsLoading(false);
    };
    fetchGames();
    }, []);

    // 추가된 useEffect
    useEffect(() => {
    setGameList(gameCtx.games);
    }, [gameCtx.games, gameList]);

    return(
        <div>
        <h2 class='title'>game List</h2>
        {isLoading && <LoadingSpinner asOverlay />}
        <Link to="/games/create"><Button>경기 생성</Button></Link>
        <Link to="/"><Button>뒤로 가기</Button></Link>
        {!isLoading && gameList.length === 0 && <div class="content-title">경기를 추가해주세요.</div>}
        {!isLoading && gameList.length > 0 && (
            <Table striped bordered hover variant="dark">
            <thead>
              <tr>
                <th>경기명</th>
                <th>위치</th>
                <th>상대</th>
                <th>점수</th>
                <th>날짜</th>
                {positions.map((position) => (
                  <th key={position}>{position}</th>
                ))}
              </tr>
            </thead>
            <tbody>
              {gameList
                .sort((a, b) => new Date(b.createdAt) - new Date(a.createdAt)) // createdAt 날짜 순으로 정렬
                .map((game) => (
                  <tr key={game.id}>
                    <td
                      onClick={() =>
                        navigate(`/games/detail`, { state: { id: game.id } })
                      }
                      style={{ cursor: "pointer" }}
                    >
                      {game.gameName}
                    </td>
                    <td>{game.location}</td>
                    <td>{game.opponent}</td>
                    <td>
                      {game.gf} vs {game.ga}
                    </td>
                    <td>{game.createdAt}</td>
                    {positions.map((position) => (
                      <td key={position}>
                        {game.gamePlayerResponseDto
                          .filter(
                            (playerDto) =>
                              playerDto.recordResponseDto.gamePosition === position
                          )
                          .map((playerDto) => (
                            <div key={playerDto.id}>{playerDto.name}</div>
                          ))}
                      </td>
                    ))}
                  </tr>
                ))}
            </tbody>
          </Table>
        )}
    </div>
    )
}

export default GameList;