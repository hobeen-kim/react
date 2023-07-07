import React, { useState, useEffect, useContext, useRef } from 'react';
import { PlayerContext } from '../../store/player/player-context';
import CreatePlayer from './CreatePlayer';
import Button from 'react-bootstrap/Button';
import { useNavigate } from "react-router-dom";
import PlayerTable from './PlayerTable';
import PlayerRecord from './PlayerRecord';
import '../../public/css/playerlist.css';




const PlayerList = () => {
  const [selectedPlayer, setSelectedPlayer] = useState(null);
  const playerCtx = useContext(PlayerContext);
  const [showCreatePlayer, setShowCreatePlayer] = useState(false);
  const recordNameInteg = playerCtx.recordNameInteg;
  const navigate = useNavigate();

  //플레이어 추가 버튼을 누르면 플레이어 추가 페이지로 이동하는 함수
  const handleCreatePlayerClick = () => {
    setShowCreatePlayer(true);
  };

  //플레이어 추가 페이지에서 취소 버튼을 누르면 플레이어 추가 페이지를 닫는 함수
  const handleCancelClick = () => {
    setShowCreatePlayer(false);
  };

  if (showCreatePlayer) {
    return <CreatePlayer onCancel={handleCancelClick}/>;
  }

  return (
    <div className='playerlist'>
      <div className='playerlist-header'>
        <h2 className='title'>Player List</h2>
        <div className='playerlist-button'>
          <Button variant="light" onClick={handleCreatePlayerClick}>선수 추가</Button>
          <Button onClick={()=>navigate('/')}>뒤로 가기</Button>
        </div>
      </div>
      <div className='playerlist-list'>
      <PlayerTable className='playerlist-table' setSelectedPlayer={setSelectedPlayer}/>
      <PlayerRecord className='playerlist-record' selectedPlayer={selectedPlayer} recordNameInteg={recordNameInteg} />
      </div>
  </div>
      )
  
};

export default PlayerList;
