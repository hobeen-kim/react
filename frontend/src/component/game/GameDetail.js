import { useLocation, useNavigate, Link } from 'react-router-dom';
import React, { useContext, useEffect, useState, useRef } from 'react';
import { GameContext} from '../../store/game/game-context';
import DatePicker from 'react-datepicker';
import 'react-datepicker/dist/react-datepicker.css';
import LoadingSpinner from '../../UI/LoadingSpinner';
import { Table, Button, Form } from 'react-bootstrap';
import classes from '../../public/css/common.module.css';

const GameDetail = () => {

//<----ㅣ----ㅣ----ㅣ----필요한 const 설정들----ㅣ----ㅣ----ㅣ---->
  const location = useLocation();
  const id = location.state?.id;
  const gameCtx = useContext(GameContext);
  const navigate = useNavigate();
  const [selectedGame, setGame] = useState(null);
  const [isLoading, setIsLoading] = useState(false);
  const [createdAt, setCreatedAt] = useState(selectedGame?.createdAt ? new Date(selectedGame.createdAt) : new Date());
  const recordName = gameCtx.recordName;
  const recordNameInteg = gameCtx.recordNameInteg;
  const positions = gameCtx.positions;

  //<----ㅣ----ㅣ----각종 State 설정을 위한 값---ㅣ----ㅣ---->
//Game 수정을 위한 boolean 값
const [gameEditState, setGameEditState] = useState(false);

//플레이어 추가, 삭제, 기록 수정을 위한 값
const [editPlayerId, setEditPlayerId] = useState('init');
const [selectedPlayer, setSelectedPlayer] = useState(null); //addplayerstate 가 바뀔때마다 렌더링
const [addPlayerState, setAddPlayerState] = useState(false);
const [playerEditState, setPlayerEditState] = useState(false);
//플레이어 수정 페이지의 체크박스 체크 여부에 따라 timeIn, timeOut 을 활성화/비활성화
const [timeInChecked, setTimeInChecked] = useState(false);
const [timeOutChecked, setTimeOutChecked] = useState(false);

//<----ㅣ----ㅣ----input 을 받기 위해 필요한 설정들---ㅣ----ㅣ---->
//Game 수정에 필요한 정보를 입력받는 input
  const GameNameInputRef = useRef(null);
  const OpponentInputRef = useRef(null);
  const LocationInputRef = useRef(null);
  const GFInputRef = useRef(null);
  const GAInputRef = useRef(null);
//player 를 추가하는 정보를 입력받는 Input
  const AddplayerIdInputRef = useRef(null);
  const AddGamePositionInputRef = useRef(null);
  const AddMainSubInputRef = useRef(null);
  //player 기록을 변경하는 정보를 입력받는 input
  const mainInputRef = useRef(null);
  const gamePositionInputRef = useRef(null);
  const touchInputRef = useRef(null);
  const goalInputRef = useRef(null);
  const assistInputRef = useRef(null);
  const chanceMakingInputRef = useRef(null);
  const shootInputRef = useRef(null);
  const validShootInputRef = useRef(null);
  const dribbleInputRef = useRef(null);
  const successDribbleInputRef = useRef(null);
  const passInputRef = useRef(null);
  const successPassInputRef = useRef(null);
  const longPassInputRef = useRef(null);
  const successLongPassInputRef = useRef(null);
  const crossPassInputRef = useRef(null);
  const successCrossPassInputRef = useRef(null);
  const tackleInputRef = useRef(null);
  const interceptInputRef = useRef(null);
  const contentionInputRef = useRef(null);
  const successContentionInputRef = useRef(null);
  const turnoverInputRef = useRef(null);
  const timeInInputRef = useRef(null);
  const timeOutInputRef = useRef(null);

//<----ㅣ----ㅣ----ㅣ----최초 게임 렌더링을 위한 코드---ㅣ----ㅣ----ㅣ---->
useEffect(() => {
    const fetchGame = async () => {
      setIsLoading(true);
      await gameCtx.getGame(id);
      setIsLoading(false);
    }
    fetchGame();
  }, [addPlayerState, playerEditState, gameEditState]);

  useEffect(() => {
    setGame(gameCtx.selectedGame);
  }, [gameCtx.selectedGame]);


//<----ㅣ----ㅣ----ㅣ----게임 수정에 필요한 함수---ㅣ----ㅣ----ㅣ---->
  //Game 의 내용만 수정하는 updateGame(게임명, 상대팀, 경기장, 득점, 실점, 경기일자)
  const handleUpdateGame = async (gameId, gameName, opponent, location, GF, GA, createdAt) => {
    setIsLoading(true);
    await gameCtx.updateGame(gameId, gameName, opponent, location, GF, GA, createdAt);
    setGameEditState(false);
    setIsLoading(false);
  }
  //handleUpdateGame 을 실행하는 함수
  const handleUpdateGameClick = async () => {
    const confirmEdit = window.confirm('경기 내용을 수정하시겠습니까?');
    if(confirmEdit) {
      const gameNameInput = GameNameInputRef.current.value;
      const opponentInput = OpponentInputRef.current.value;
      const locationInput = LocationInputRef.current.value;
      const GFInput = parseInt(GFInputRef.current.value);
      const GAInput = parseInt(GAInputRef.current.value);
      const createdAtInput = createdAt;
      handleUpdateGame(selectedGame.id, gameNameInput, opponentInput, locationInput, GFInput, GAInput, createdAtInput);
    }
  }
  //Game 의 내용을 삭제하는 함수
  const handleDeleteGame = async (gameId) => {
    const confirmEdit = window.confirm('경기를 삭제하시겠습니까?');
    if(confirmEdit){
      setIsLoading(true);
      await gameCtx.deleteGame(gameId);
      setIsLoading(false);
      navigate('/games');
    }  
  }
  //<----ㅣ----ㅣ----ㅣ----플레이어 전체 기록을 보여주기 위해 필요한 함수---ㅣ----ㅣ----ㅣ---->
  //플레이어 전체 기록을 보여주기 위한 함수
  const sumPlayerRecord = (recordName) => {
    let sum = 0;
    selectedGame.gamePlayerResponseDto.forEach(player => {
      sum += player.recordResponseDto[recordName];
    })
    return sum;
  }


  //<----ㅣ----ㅣ----ㅣ----플레이어 수정에 필요한 함수---ㅣ----ㅣ----ㅣ---->


  //경기에 플레이어 추가(경기에만 추가됨)
  const handleAddPlayer = async () => {
    setAddPlayerState(true);
    const gameId = selectedGame.id;
    const playerId = parseInt(AddplayerIdInputRef.current.value);
    const gamePosition = AddGamePositionInputRef.current.value;
    const mainSub = AddMainSubInputRef.current.value;

    setIsLoading(true);
    await gameCtx.addPlayer(gameId, playerId, gamePosition, mainSub);
    setIsLoading(false);
    setAddPlayerState(false);

  }

  //경기에 참여한 플레이어 삭제(경기에서만 삭제됨)
  const handleDeletePlayer = async (playerId) => {
    const confirmEdit = window.confirm('Are you sure you want to delete this player?');
    if(confirmEdit){
      setIsLoading(true);
      await gameCtx.deletePlayer(selectedGame.id, playerId);
      setIsLoading(false);
    }
  }

  //플레이어의 경기 기록을 수정하는 함수
  const handleUpdatePlayer = async () => {
    setIsLoading(true);
    const gamePosition = gamePositionInputRef.current.value;
    const main = mainInputRef.current.value;
    const touch = parseInt(touchInputRef.current.value);
    const goal = parseInt(goalInputRef.current.value);
    const assist = parseInt(assistInputRef.current.value);
    const chanceMaking = parseInt(chanceMakingInputRef.current.value);
    const shoot = parseInt(shootInputRef.current.value);
    const validShoot = parseInt(validShootInputRef.current.value);
    const dribble = parseInt(dribbleInputRef.current.value);
    const successDribble = parseInt(successDribbleInputRef.current.value);
    const pass = parseInt(passInputRef.current.value);
    const successPass = parseInt(successPassInputRef.current.value);
    const longPass = parseInt(longPassInputRef.current.value);
    const successLongPass = parseInt(successLongPassInputRef.current.value);
    const crossPass = parseInt(crossPassInputRef.current.value);
    const successCrossPass = parseInt(successCrossPassInputRef.current.value);
    const tackle = parseInt(tackleInputRef.current.value);
    const intercept = parseInt(interceptInputRef.current.value);
    const contention = parseInt(contentionInputRef.current.value);
    const successContention = parseInt(successContentionInputRef.current.value);
    const turnover = parseInt(turnoverInputRef.current.value);
    const timeIn = timeInChecked ? parseInt(timeInInputRef.current.value) : 0;
    const timeOut = timeOutChecked ? parseInt(timeOutInputRef.current.value) : 0;

    await gameCtx.updatePlayer(selectedGame.id, selectedPlayer.id, timeIn, timeOut, gamePosition, main, touch, goal, assist, chanceMaking, shoot, validShoot, dribble, successDribble, pass, successPass, longPass, successLongPass, crossPass, successCrossPass, tackle, intercept, contention, successContention, turnover);
    setIsLoading(false);
    setPlayerEditState(false);
  }

  //플레이어 포지션 변경 시 tr 의 색깔을 변경하는 함수
  const handleGamePositionChange = (position) => {
    if(['GK', 'RB', 'RCB', 'LCB', 'LB', 'CB'].includes(position)) return 'detail-defence';
    else if(['RM', 'RCM', 'LCM', 'LM', 'CDM'].includes(position)) return 'detail-attack';
    else if(['RS', 'LS', 'CF', 'CF'].includes(position)) return 'detail-midfield';
  
  }


  if(!selectedGame) {
    return <h2>Game Not Found</h2>
  }
  
  return (
    <div>
  {/**경기의 수정, 삭제, 취소 등 버튼 시작---->*/}

      <h2 class='title'>Game Detail</h2>
      {isLoading && <LoadingSpinner />}
      {gameEditState ? 
      (<>
        <Button onClick={handleUpdateGameClick}>수정완료</Button>
        <Button onClick={() => setGameEditState(false)}>뒤로 가기</Button>
      </>):
      (<>
        <Button onClick={() => setGameEditState(true)}>게임 수정</Button>
        <Link to="/games"><Button>뒤로 가기</Button></Link>
        <Button onClick={()=>navigate('/games/hitmap', { state: { id: selectedGame.id }})}>히트맵</Button>
        <Button onClick={()=>handleDeleteGame(id)}>게임 삭제</Button>

      </>)}
      {gameEditState ? (
      <>
  {/**<----경기의 수정, 삭제, 취소 등 버튼 끝*/}
  {/**경기의 상세정보 수정 시작---->*/}
      <p><span class='content'>경기명  : </span><input class='input-box-wide' type="text" defaultValue={selectedGame.gameName} required ref={GameNameInputRef}/></p>
      <p><span class='content' style={{marginRight:'15px'}}> 위치 : </span><input class='input-box-wide' type="text" defaultValue={selectedGame.location} required ref={LocationInputRef}/></p>
      <p><span class='content' style={{marginRight:'15px'}}>상대  : </span><input class='input-box-wide' type="text" defaultValue={selectedGame.opponent} required ref={OpponentInputRef}/></p>
      <p><span class='content' style={{marginRight:'15px'}}>득점  : </span><input class='input-box-wide' type="text" defaultValue={selectedGame.gf} required ref={GFInputRef}/></p>
      <p><span class='content' style={{marginRight:'15px'}}>실점  : </span><input class='input-box-wide' type="text" defaultValue={selectedGame.ga} required ref={GAInputRef}/></p>
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
  {/**---->경기의 상세정보 수정 끝 */}
        </>
      ) : (     
      <>
  {/**경기의 상세정보 조회 시작---->*/}

      <p class='detail-content'>경기명 : {selectedGame.gameName}</p>
      <p class='detail-content'>위치 : {selectedGame.location}</p>
      <p class='detail-content'>상대 : {selectedGame.opponent}</p>
      <p class='detail-content'>결과 : {selectedGame.gf} vs {selectedGame.ga}</p>
      <p class='detail-content'>날짜 : {selectedGame.createdAt}</p>
  {/**---->경기의 상세정보 조회 끝*/}
  {/*참가 선수의 세부기록을 보여주는 테이블 시작----|---->*/}
  <Table striped bordered hover style={{backgroundColor:"white"}}>
  <thead class='detail-thead'>
    <tr>
      <th>이름</th>
      {recordNameInteg.map((name) => (
      <th key={name}>{name}</th>))}
      <th>delete</th>
    </tr>
  </thead>
  <tbody class="detail-tbody">
    {positions.map((position) => {
      const players = selectedGame.gamePlayerResponseDto.filter((player) => player.recordResponseDto.gamePosition === position && player.recordResponseDto.main === 'MAIN');
      return (
        <>
          {players.map((player, index) => (
            <tr key={`${position}-${index}`} class={handleGamePositionChange(position)}>
              <td  style={{ cursor: 'pointer' }} onClick={() => {setSelectedPlayer(player); setEditPlayerId(player.id); setPlayerEditState(!playerEditState);}}>
                  {player.name}({player.recordResponseDto.gamePosition})
                  {player.recordResponseDto.timeIn ? <><br/>({player.recordResponseDto.timeIn}분 <span class='time-in'>in</span>)</> : null}
                  {player.recordResponseDto.timeOut ? <><br/>({player.recordResponseDto.timeOut}분 <span class='time-out'>out</span>)</> : null}
              </td>
              <td>{player.recordResponseDto.touch !== 0? player.recordResponseDto.touch: `-`}</td>
              <td>{player.recordResponseDto.goal !== 0? player.recordResponseDto.goal: `-`}</td>
              <td>{player.recordResponseDto.assist !== 0? player.recordResponseDto.assist: `-`}</td>
              <td>{player.recordResponseDto.chanceMaking !== 0? player.recordResponseDto.chanceMaking: `-`}</td>
              <td>{player.recordResponseDto.shoot !== 0? `${player.recordResponseDto.shoot}(${player.recordResponseDto.validShoot})`: `-`}</td>
              <td>{player.recordResponseDto.dribble !== 0? `${player.recordResponseDto.dribble}(${player.recordResponseDto.successDribble})`: `-`}</td>
              <td>{player.recordResponseDto.pass !== 0? `${player.recordResponseDto.pass}(${player.recordResponseDto.successPass})`: `-`}
                  {player.recordResponseDto.pass !== 0? (`, ${(player.recordResponseDto.successPass / player.recordResponseDto.pass * 100).toFixed(0)}%`) : null}</td>
              <td>{player.recordResponseDto.longPass !== 0? `${player.recordResponseDto.longPass}(${player.recordResponseDto.successLongPass})`: `-`}</td>
              <td>{player.recordResponseDto.crossPass !== 0? `${player.recordResponseDto.crossPass}(${player.recordResponseDto.successCrossPass})`: `-`}</td>
              <td>{player.recordResponseDto.tackle !== 0? player.recordResponseDto.tackle: `-`}</td>
              <td>{player.recordResponseDto.intercept !== 0? player.recordResponseDto.intercept: `-`}</td>
              <td>{player.recordResponseDto.contention !== 0? `${player.recordResponseDto.contention}(${player.recordResponseDto.successContention})`: `-`}</td>
              <td>{player.recordResponseDto.turnover !== 0? player.recordResponseDto.turnover: `-`}</td>
              <td><Button onClick={() => {handleDeletePlayer(player.id)}} disabled={playerEditState}>삭제</Button></td>
            </tr>
          ))}
        </>
      );
    })}
    {positions.map((position) => {
      const players = selectedGame.gamePlayerResponseDto.filter((player) => player.recordResponseDto.gamePosition === position && player.recordResponseDto.main === 'SUB');
      return (
        <>
          {players.map((player, index) => (
            <tr key={`${position}-${index}`} class='detail-sub'>
              <td  style={{ cursor: 'pointer' }} onClick={() => {setSelectedPlayer(player); setEditPlayerId(player.id); setPlayerEditState(!playerEditState);}}>
                  {player.name}({player.recordResponseDto.gamePosition})
                  {player.recordResponseDto.timeIn ? <><br/>({player.recordResponseDto.timeIn}분 <span class='time-in'>in</span>)</> : null}
                  {player.recordResponseDto.timeOut ? <><br/>({player.recordResponseDto.timeOut}분 <span class='time-out'>out</span>)</> : null}
              </td>
              <td>{player.recordResponseDto.touch !== 0? player.recordResponseDto.touch: `-`}</td>
              <td>{player.recordResponseDto.goal !== 0? player.recordResponseDto.goal: `-`}</td>
              <td>{player.recordResponseDto.assist !== 0? player.recordResponseDto.assist: `-`}</td>
              <td>{player.recordResponseDto.chanceMaking !== 0? player.recordResponseDto.chanceMaking: `-`}</td>
              <td>{player.recordResponseDto.shoot !== 0? `${player.recordResponseDto.shoot}(${player.recordResponseDto.validShoot})`: `-`}</td>
              <td>{player.recordResponseDto.dribble !== 0? `${player.recordResponseDto.dribble}(${player.recordResponseDto.successDribble})`: `-`}</td>
              <td>{player.recordResponseDto.pass !== 0? `${player.recordResponseDto.pass}(${player.recordResponseDto.successPass})`: `-`}
                  {player.recordResponseDto.pass !== 0? (`, ${(player.recordResponseDto.successPass / player.recordResponseDto.pass * 100).toFixed(0)}%`) : null}</td>
              <td>{player.recordResponseDto.longPass !== 0? `${player.recordResponseDto.longPass}(${player.recordResponseDto.successLongPass})`: `-`}</td>
              <td>{player.recordResponseDto.crossPass !== 0? `${player.recordResponseDto.crossPass}(${player.recordResponseDto.successCrossPass})`: `-`}</td>
              <td>{player.recordResponseDto.tackle !== 0? player.recordResponseDto.tackle: `-`}</td>
              <td>{player.recordResponseDto.intercept !== 0? player.recordResponseDto.intercept: `-`}</td>
              <td>{player.recordResponseDto.contention !== 0? `${player.recordResponseDto.contention}(${player.recordResponseDto.successContention})`: `-`}</td>
              <td>{player.recordResponseDto.turnover !== 0? player.recordResponseDto.turnover: `-`}</td>
              <td><Button onClick={() => {handleDeletePlayer(player.id)}} disabled={playerEditState}>삭제</Button></td>
            </tr>
          ))}
        </>
      );
    })}
    <tr>
      <td>팀 기록</td>
      <td>{sumPlayerRecord('touch')}</td>
      <td>{sumPlayerRecord('goal')}</td>
      <td>{sumPlayerRecord('assist')}</td>
      <td>{sumPlayerRecord('chanceMaking')}</td>
      <td>{sumPlayerRecord('shoot')}({sumPlayerRecord('validShoot')})</td>
      <td>{sumPlayerRecord('dribble')}({sumPlayerRecord('successDribble')})</td>
      <td>{sumPlayerRecord('pass')}({sumPlayerRecord('successPass')})
        {sumPlayerRecord('pass') !== 0 ? (`, ${(sumPlayerRecord('successPass') / sumPlayerRecord('pass') * 100).toFixed(0)}%`):'null'}
      </td>
      <td>{sumPlayerRecord('longPass')}({sumPlayerRecord('successLongPass')})</td>
      <td>{sumPlayerRecord('crossPass')}({sumPlayerRecord('successCrossPass')})</td>
      <td>{sumPlayerRecord('tackle')}</td>
      <td>{sumPlayerRecord('intercept')}</td>
      <td>{sumPlayerRecord('contention')}({sumPlayerRecord('successContention')})</td>
      <td>{sumPlayerRecord('turnover')}</td>
      <td></td>
    </tr>
  </tbody>
</Table>
{/*참가 선수 추가 시작----|---->*/}
<div class='flex-container'>
<Form.Select className='select-box-wide' id='addPlayer' ref={AddplayerIdInputRef}>
  {Object.entries(selectedGame.allPlayers).map(([id, name]) => (
    <option key={id} value={id}>{name}</option>
  ))}
</Form.Select>
  <Form.Select className='select-box-wide' id='addPosition' ref={AddGamePositionInputRef}>
  {positions.map((position) => (
    <option key={position} value={position}>{position}</option>
  ))}
  </Form.Select>
  <Form.Select className='select-box-wide' id='mainSub' ref={AddMainSubInputRef}>
    <option key='main' value='MAIN'>선발</option>
    <option key='sub' value='SUB'>교체</option>
  </Form.Select>
<Button onClick={handleAddPlayer}>추가</Button>
</div>

{/*----|---->참가 선수 추가 끝*/}
{/*참가 선수 중 selectedPlayer 수정 페이지 시작----|---->*/}
{playerEditState && (
  <>
  <h2>{selectedPlayer?.name} 선수 기록 수정</h2>
  <Table striped hover borderless style={{backgroundColor:"#beb7b7", borderRadius:"15px"}}>
    <thead>
      <tr>
        <th>경기 포지션</th>
        <th>선발 여부</th>
        {recordName.map((name, index) => (
          <th key={`${name}-${index}`}>{name}</th>
        ))}
        <th>time In</th>
        <th/>
        <th>time Out</th>
        <th/>
      </tr>
    </thead>
    <tbody>
      <tr>
      <td>
        <Form.Select className='select-box-wide' id='position' defaultValue={selectedPlayer?.recordResponseDto.gamePosition} ref={gamePositionInputRef}>
          {positions.map((position) => (
            <option key={position} value={position}>{position}</option>
          ))}
          </Form.Select>
     </td>
     <td>
        <Form.Select className='select-box-wide' id='main' defaultValue={selectedPlayer?.recordResponseDto.main} ref={mainInputRef}>
            <option key='MAIN' value='MAIN'>선발</option>
            <option key='SUB' value='SUB'>교체</option>
          </Form.Select>
     </td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.touch} ref={touchInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.goal} ref={goalInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.assist} ref={assistInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.chanceMaking} ref={chanceMakingInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.shoot} ref={shootInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.validShoot} ref={validShootInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.dribble} ref={dribbleInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.successDribble} ref={successDribbleInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.pass} ref={passInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.successPass} ref={successPassInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.longPass} ref={longPassInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.successLongPass} ref={successLongPassInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.crossPass} ref={crossPassInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.successCrossPass} ref={successCrossPassInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.tackle} ref={tackleInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.intercept} ref={interceptInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.contention} ref={contentionInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.successContention} ref={successContentionInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.turnover} ref={turnoverInputRef} /></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.timeIn} ref={timeInInputRef} disabled={!timeInChecked}/></td>
      <td><input type="checkbox" checked={timeInChecked} onChange={()=> setTimeInChecked(!timeInChecked)}/></td>
      <td><input type="number" className={classes['input-box']} defaultValue={selectedPlayer?.recordResponseDto.timeOut} ref={timeOutInputRef}  disabled={!timeOutChecked}/></td>
      <td><input type="checkbox" checked={timeOutChecked} onChange={()=> setTimeOutChecked(!timeOutChecked)}/></td>
      </tr>
    </tbody>
  </Table>
  <Button onClick={() => {handleUpdatePlayer();}}>수정 완료</Button>
  </>
)}
      </>
      

      )}
    </div>
  )
}

export default GameDetail;