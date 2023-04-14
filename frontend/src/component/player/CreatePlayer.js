import { PlayerContext } from '../../store/player/player-context';
import LoadingSpinner from '../../UI/LoadingSpinner'
import React, { useState, useRef, useContext } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { Validation } from '../../store/validation/validation';

const CreatePlayer = ({ onCancel }) => {

  const playerCtx = useContext(PlayerContext);
  const validation = useContext(Validation);
  const PlayerNameInputRef = useRef(null);
  const PlayerPositionInputRef = useRef(null);
  const [isLoading, setIsLoading] = useState(false);
  const [playerNameError, setPlayerNameError] = useState('');
  
  const positions = playerCtx.positions;

  const onhandlePost = async (playerName, playerPosition) => {
    setIsLoading(true);
    playerCtx.createPlayer(playerName, playerPosition);
    setIsLoading(false);
    onCancel();
  };

  const submitHandler = async (event) => {    
    event.preventDefault();

    const playerName = PlayerNameInputRef.current.value;
    const playerPosition = PlayerPositionInputRef.current.value;


    //플레이어 이름 유효성 체크
    const playerNameCheck = validation.nameValidator(playerName, 1, 30);
    setPlayerNameError(playerNameCheck);

    if(!playerNameCheck) {
      console.log("onhandlePost");
      onhandlePost(playerName, playerPosition);
    }

  }


  const handleCancelClick = () => {
    if (onCancel) {
      onCancel();
    }
};

  return (
    <section>
      <h1 class='title'>add Player</h1>
      <div style={{width:"400px"}}>
      <form onSubmit={submitHandler}>
        <div>
          <label htmlFor='playerName' class='content'>선수 이름 : </label>
          <input class='input-box' type='text' id='playerName' style={{marginLeft:'15px', width:'150px'}} required ref={PlayerNameInputRef}/>
          <div className='error'>{playerNameError}</div>
        </div>
        <div style={{display:"flex", marginTop : '20px'}}>
        <label htmlFor="position" class='content'>포지션 : </label>
          <Form.Select class='select-box' id='position' 
          style={{marginLeft:'15px', width:'150px'}} required ref={PlayerPositionInputRef}>
          {positions.map((position) => (
            <option key={position} value={position}>{position}</option>
          ))}
          </Form.Select>
        </div>
        <div style={{marginLeft:'120px', marginTop:'20px', width:'300px'}}>
          <Button type='submit'>추가</Button>
          <Button type='button' onClick={handleCancelClick}>취소</Button>
          {isLoading && <LoadingSpinner/>}
        </div>
      </form>
            </div>
    </section>
  );
};

export default CreatePlayer;
