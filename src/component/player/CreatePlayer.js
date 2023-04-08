import { PlayerContext } from '../../store/player/player-context';
import LoadingSpinner from '../../UI/LoadingSpinner'
import React, { useState, useRef, useContext } from 'react';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

const CreatePlayer = ({ onCancel }) => {

  const PlayerNameInputRef = useRef(null);
  const PlayerPositionInputRef = useRef(null);
  
  const [isLoading, setIsLoading] = useState(false);
  const playerCtx = useContext(PlayerContext);
  const positions = playerCtx.positions;

  const submitHandler = async () => {    
    const enteredPlayerName = PlayerNameInputRef.current.value;
    const enteredPlayerPosition = PlayerPositionInputRef.current.value;

    setIsLoading(true);
    playerCtx.createPlayer(enteredPlayerName, enteredPlayerPosition);
    setIsLoading(false);

    handleCancelClick();
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
