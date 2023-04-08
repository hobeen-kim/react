import { useNavigate } from 'react-router-dom';
import React, { useContext, useEffect, useState, useRef } from 'react';
import { GameContext} from '../../store/game/game-context';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import LoadingSpinner from '../../UI/LoadingSpinner';
import { Button, Table, Form } from 'react-bootstrap';


const CreateGame = () => {

    const gameCtx = useContext(GameContext);
    const [createInit, setCreateInit] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const navigate = useNavigate();
    const positions = gameCtx.positions;

    useEffect(() => {
        const fetchCreateInit = async () => {
        setIsLoading(true);
        await gameCtx.createInitGame();
        setCreateInit(gameCtx.createInit);
        setIsLoading(false);
    };
    fetchCreateInit();
    }, []);

    useEffect(() => {
        setCreateInit(gameCtx.createInit);
      }, [gameCtx.createInit]);

    const GameNameInputRef = useRef(null);
    const OpponentInputRef = useRef(null);
    const LocationInputRef = useRef(null);
    const GFInputRef = useRef(null);
    const GAInputRef = useRef(null);
    const [createdAt, setCreatedAt] = useState(new Date());
    const positionRefs = Array(positions.length * 3).fill(0)?.map(React.createRef);

    const submitHandler = () => {
        let duplicateState = false;
        const ReqPlayers = [];
        const playersInput = positionRefs.map((positionRef) => {
            return positionRef.current.value;
        });
        playersInput.map((playerInput, index) => {
          if (playerInput !== 'none') {
              const player = {
                  playerId: parseInt(playerInput),
                  gamePosition: positions[index % 14],
                  main : index < 14 ? 'MAIN' : 'SUB'
                };
              ReqPlayers.map((ReqPlayer) => {
                  if (ReqPlayer.playerId === player.playerId) {
                      duplicateState = true;
                  }})
              ReqPlayers.push(player);
          }})
          if (duplicateState) {
              alert('중복된 선수가 있습니다.');
          } else if(window.confirm('경기를 생성하시겠습니까?')){
              CreateGameHandler(ReqPlayers);
          } 
    }


    const CreateGameHandler = async (ReqPlayers) => {
        setIsLoading(true);
        const gameNameInput = GameNameInputRef.current.value;
        const opponentInput = OpponentInputRef.current.value;
        const locationInput = LocationInputRef.current.value;
        const GFInput = GFInputRef.current.value;
        const GAInput = GAInputRef.current.value;
        const createdAtInput = createdAt;
        
        gameCtx.createGame(gameNameInput, opponentInput, locationInput, GFInput, GAInput, createdAtInput, ReqPlayers);
        setIsLoading(false);   
        navigate('/games');
    }

    return (
    <div>
    <h2 class='title'>Create Game</h2>
    <Button onClick={()=>submitHandler()} style={{marginLeft:'70px'}}>생성 완료</Button>
    <Button onClick={()=>navigate('/games')}>뒤로 가기</Button>
    {isLoading && <LoadingSpinner asOverlay />}
    <p><span class='content'>경기명  : </span><input class='input-box-wide' type="text" required ref={GameNameInputRef}/></p>
      <p><span class='content' style={{marginRight:'15px'}}>위치  : </span><input class='input-box-wide' type="text" required ref={LocationInputRef}/></p>
      <p><span class='content' style={{marginRight:'15px'}}>상대  : </span><input class='input-box-wide' type="text" required ref={OpponentInputRef}/></p>
      <p><span class='content' style={{marginRight:'15px'}}>득점  : </span><input class='input-box-wide' type="number" required ref={GFInputRef}/></p>
      <p><span class='content' style={{marginRight:'15px'}}>실점  : </span><input class='input-box-wide' type="number" required ref={GAInputRef}/></p>
      <p class='flex-container'><span class='content' style={{marginRight:'-15px'}}>날짜  : </span><DatePicker 
            selected={createdAt}
            onChange={(date) => setCreatedAt(date)} 
            dateFormat="yyyy-MM-dd" 
            required 
            showYearDropdown 
            scrollableYearDropdown
            yearDropdownItemNumber={10}
          />
      </p>
      <Table>
        <thead>
            <tr>
                <th>경기 포지션</th>
                {positions.map(position => (
                <th key={position}>{position}</th>))}
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>주전 선수</td>
                {positions.map((position, index) => (
                <td key={position}>
                    <Form.Select size="sm" style={{width:'90px'}}
                    ref={positionRefs[index]} defaultValue="none" class='select-box-wide'>
                    <option value="none">none</option>
                    {createInit?.map((player) => (
                    <option key={player.id} value={player.id}>{player.name}</option>
                  ))}
                </Form.Select>
              </td>
            ))}
            </tr>
            <tr>
                <td>교체 선수</td>
                {positions.map((position, index) => (
                <td key={position}>
                    <Form.Select size="sm" style={{width:'90px'}}
                    ref={positionRefs[index+ positions.length]} defaultValue="none">
                    <option value="none">none</option>
                    {createInit?.map((player) => (
                    <option key={player.id} value={player.id}>{player.name}</option>
                  ))}
                </Form.Select>
              </td>
            ))}
            </tr>
            <tr>
                <td>교체 선수</td>
                {positions.map((position, index) => (
                <td key={position}>
                    <Form.Select size="sm" style={{width:'90px'}}
                    ref={positionRefs[index+ positions.length * 2]} defaultValue="none">
                    <option value="none">none</option>
                    {createInit?.map((player) => (
                    <option key={player.id} value={player.id}>{player.name}</option>
                  ))}
                </Form.Select>
              </td>
            ))}
            </tr>
        </tbody>
      </Table>
    </div>
    
    )
}

export default CreateGame;
