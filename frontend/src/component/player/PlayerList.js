import React, { useState, useEffect, useContext, useRef } from 'react';
import { Table } from 'react-bootstrap';
import { PlayerContext } from '../../store/player/player-context';
import LoadingSpinner from '../../UI/LoadingSpinner'
import CreatePlayer from './CreatePlayer';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useNavigate } from "react-router-dom";



const PlayerList = () => {
  const [playerList, setPlayerList] = useState([]);
  const [sortedPlayers, setSortedPlayers] = useState([]);
  const [selectedPlayer, setSelectedPlayer] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const playerCtx = useContext(PlayerContext);
  const [showCreatePlayer, setShowCreatePlayer] = useState(false);
  const [editPlayerId, setEditPlayerId] = useState('init');
  const recordNameInteg = playerCtx.recordNameInteg;
  const positions = playerCtx.positions;
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPlayers = async () => {
      setIsLoading(true);
      playerCtx.getPlayers();
      setPlayerList(playerCtx.players);
 
      setIsLoading(false);
    };
    fetchPlayers();
    const sorted = sortPlayers(playerList, positions);
    setSortedPlayers(sorted);
  }, []);

  // 추가된 useEffect
  useEffect(() => {
    setPlayerList(playerCtx.players);
    const sorted = sortPlayers(playerList, positions);
    setSortedPlayers(sorted);
  }, [playerCtx.players, sortedPlayers]);

  //플레이어를 position 별로 정렬하는 함수
  function sortPlayers(playerList, positions) {
    const sortedPlayers = [...playerList].sort((a, b) => {
      const positionA = positions.indexOf(a.position);
      const positionB = positions.indexOf(b.position);
      return positionA - positionB;
    });
    return sortedPlayers;
  }

  //플레이어 정보를 수정하는 함수
  const handleUpdatePlayer = async (playerId, name, position) => {

    setIsLoading(true);
    await playerCtx.updatePlayer(playerId, name, position);
    setIsLoading(false);
  };

  //플레이어를 누르면 해당 플레이어의 레코드를 보여주는 함수
  const handlePlayerClick = (player) => {
    setSelectedPlayer(player);
  };

  //플레이어 추가 버튼을 누르면 플레이어 추가 페이지로 이동하는 함수
  const handleCreatePlayerClick = () => {
    setShowCreatePlayer(true);
  };

  //플레이어 추가 페이지에서 취소 버튼을 누르면 플레이어 추가 페이지를 닫는 함수
  const handleCancelClick = () => {
    setShowCreatePlayer(false);
  };

  //플레이어를 삭제하는 함수
  const handleDeletePlayer = async (playerId) => {
    setIsLoading(true);
    await playerCtx.deletePlayer(playerId);
    setIsLoading(false);
  };

  //삭제 시 2차 확인 버튼
const handleDeleteClick = (id) => {
  const confirmDelete = window.confirm(
    '이 선수를 삭제하시겠습니까?'
  );

  if(confirmDelete) {
    handleDeletePlayer(id);
  }
}

const PlayerNameInputRef = useRef(null);
const PlayerPositionInputRef = useRef(null);

//수정, 저장버튼 클릭 시 실행되는 함수
const HandleEditClick = (playerId) => {
  if (editPlayerId === playerId) {
    //2차 확인 버튼
    const confirmEdit = window.confirm(
      '이 선수의 정보를 수정하시겠습니까?'
    );
    if(confirmEdit) {
      const nameInput = PlayerNameInputRef.current.value;
      const positionInput = PlayerPositionInputRef.current.value;
      handleUpdatePlayer(playerId, nameInput, positionInput);
      setEditPlayerId('init');
    }
  } else {
    // 수정 버튼 클릭
    setEditPlayerId(playerId);
  }
};

  if (showCreatePlayer) {
    return <CreatePlayer onCancel={handleCancelClick}/>;
  }

  return (
    <div>
    <h2 class='title'>Player List</h2>
    <div style={{display:"flex", marginLeft:"200px"}}>
      <Button variant="light" onClick={handleCreatePlayerClick}>선수 추가</Button>
      <Button onClick={()=>navigate('/')}>뒤로 가기</Button>
    </div>
    {!isLoading && playerList.length === 0 && <div class="content-title">선수를 추가해주세요</div>}
    {isLoading && <LoadingSpinner />}
    {!isLoading && playerList.length> 0 && (
      <>
      
      <Table striped bordered hover variant="dark">
        <thead>
          
          <tr>
            <th>Name</th>
            <th>Position</th>
            <th></th>
            <th></th>
          </tr>
        </thead>
        <tbody>
          {sortedPlayers.map((player) => (
            <tr key={player.id}>
              <td onClick={() => handlePlayerClick(player)} style={{ cursor: 'pointer' }}>
              {editPlayerId === player.id ? (
                  <input class='input-box'
                    type="text"
                    defaultValue={player.name}
                    id={`name-input-${player.id}`}
                    required ref={PlayerNameInputRef}
                  />
                ) : (
                  player.name
                )}
              </td>
                <td onClick={() => handlePlayerClick(player)} style={{ cursor: 'pointer' }}>
                  {editPlayerId === player.id ? (
                    <Form.Select
                      class='select-box'
                      id={`position-input-${player.id}`}
                      defaultValue={player.position}
                      ref={PlayerPositionInputRef}
                    >
                      {positions.map((position) => (
                      <option key={position} value={position}>{position}</option>
                        ))}
                    </Form.Select>
                  ) : (
                    player.position
                  )}
                </td>
                 <td>
                 <Button
                  onClick={() => HandleEditClick(player.id)}
                  disabled={editPlayerId !== 'init' && editPlayerId !== player.id}>
                  {editPlayerId === player.id ? '저장' : '수정'}
                  </Button>
              </td>
              {editPlayerId === player.id ? 
              <td><Button onClick={() => setEditPlayerId('init')}>취소</Button></td> : 
              <td><Button disabled={editPlayerId !== 'init' && editPlayerId !== player.id} 
                   onClick={() => handleDeleteClick(player.id)}>삭제</Button></td>}
            </tr>
          ))}
        </tbody>

      </Table>
      </>
    )}
    {selectedPlayer && selectedPlayer.recordResponseDto.length === 0 && <div div class="content-title">There are no records.</div>}
    {selectedPlayer && selectedPlayer.recordResponseDto.length > 0 &&(
      <div>
        <div class="content-title">{selectedPlayer.name} 선수의 경기 기록</div>
        <Table striped bordered hover variant="dark">
          <thead>
            <tr>
              <th>참여 경기</th>
              <th>일자</th>
              <th>경기 포지션</th>
              {recordNameInteg.map((name) => (
              <th key={name}>{name}</th>))}
            </tr>
          </thead>
          <tbody>
            {selectedPlayer.recordResponseDto.map((record) => (
              <tr key={record.id}>
                <td>{record.gameName}</td>
                <td>{record.createdAt}</td>
                <td>{record.gamePosition}
                    {record.timeIn ? <><br/>({record.timeIn}분 <span style={{color: 'green'}}>in</span>)</> : null}
                    {record.timeOut ? <><br/>({record.timeOut}분 <span style={{color: 'red'}}>out</span>)</> : null}
                </td>
                <td>{record.touch}</td>
                <td>{record.goal}</td>
                <td>{record.assist}</td>
                <td>{record.chanceMaking}</td>
                <td>{`${record.shoot} (${record.validShoot})`}</td>
                <td>{`${record.dribble} (${record.successDribble})`}</td>
                <td>{`${record.pass} (${record.successPass})`}</td>
                <td>{`${record.longPass} (${record.successLongPass})`}</td>
                <td>{`${record.crossPass} (${record.successCrossPass})`}</td>
                <td>{record.tackle}</td>
                <td>{record.intercept}</td>
                <td>{`${record.contention} (${record.successContention})`}</td>
                <td>{record.turnover}</td>
              </tr>
            ))}
          </tbody>
        </Table>
      </div>
    )}
  </div>
      )
  
};

export default PlayerList;
