import React, { useState, useEffect, useContext, useRef } from 'react';
import { Table } from 'react-bootstrap';
import LoadingSpinner from '../../UI/LoadingSpinner'
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { PlayerContext } from '../../store/player/player-context';

const PlayTable = ({setSelectedPlayer}) => {

    const [playerList, setPlayerList] = useState([]);
    const [isLoading, setIsLoading] = useState(false);
    const [sortedPlayers, setSortedPlayers] = useState([]);
    const playerCtx = useContext(PlayerContext);
    const [editPlayerId, setEditPlayerId] = useState('init');
    const positions = playerCtx.positions;



  useEffect(() => {
        const fetchPlayers = () => {
        setIsLoading(true);
        playerCtx.getPlayers();
        setPlayerList(playerCtx.players);
        setIsLoading(false);
        };
        fetchPlayers();
    }, []);

  // playerList와 positions이 변경될 때마다 실행되도록 수정
useEffect(() => {
    setPlayerList(playerCtx.players);
    const sorted = sortPlayers(playerCtx.players, positions);
    setSortedPlayers(sorted);
    }, [playerCtx.players, positions]);

    function sortPlayers(playerList, positions) {
    const sortedPlayers = [...playerList]?.sort((a, b) => {
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

    return (
        <div>
            {!isLoading && playerList.length === 0 && <div class="content-title">선수를 추가해주세요</div>}
            {isLoading && <LoadingSpinner />}
            {!isLoading && playerList.length > 0 && (
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
                {sortedPlayers?.map((player) => (
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
            )}    
    </div>
    );

}

export default PlayTable;