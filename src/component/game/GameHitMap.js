import { useLocation, useNavigate, Link } from 'react-router-dom';
import { useState, useRef } from 'react';
import soccerField from '../../public/image/soccerField.jpg';
import classes from '../../public/css/createGameField.module.css';
import { GameContext } from '../../store/game/game-context';
import { useContext, useEffect } from 'react';
import classnames from 'classnames';
import '../../public/css/index.css';
import Slider from 'rc-slider';
import "./index.less"
import { handleRender } from "./TooltipSlider"
import ButtonGroup from 'react-bootstrap/ButtonGroup';
import ToggleButton from 'react-bootstrap/ToggleButton';
import { Table, Button, Form } from 'react-bootstrap';
import blueImage from '../../public/image/-1.png';
import Image1 from '../../public/image/1.png';
import Image2 from '../../public/image/2.png';
import Image3 from '../../public/image/3.png';
import Image4 from '../../public/image/4.png';
import Image5 from '../../public/image/5.png';
import Image6 from '../../public/image/6.png';
import Image7 from '../../public/image/7.png';
import Image8 from '../../public/image/8.png';
import Image9 from '../../public/image/9.png';
import Image0 from '../../public/image/0.png';
import ImageQ from '../../public/image/q.png';
import ImageW from '../../public/image/w.png';
import ImageE from '../../public/image/e.png';
import ImageR from '../../public/image/r.png';
import ImageT from '../../public/image/t.png';
import ImageY from '../../public/image/y.png';
import ImageU from '../../public/image/u.png';
import ImageI from '../../public/image/i.png';
import ImageO from '../../public/image/o.png';
import ImageP from '../../public/image/p.png';
import ImageA from '../../public/image/a.png';
import ImageS from '../../public/image/s.png';
import ImageZ from '../../public/image/z.png';
import ImageShift from '../../public/image/shift.png';
import ImageCtrl from '../../public/image/ctrl.png';
import ImageAlt from '../../public/image/alt.png';



const GameHitMap = () => {

    const Images = {
        "-1": blueImage,
        "1": Image1,
        "2": Image2,
        "3": Image3,
        "4": Image4,
        "5": Image5,
        "6": Image6,
        "7": Image7,
        "8": Image8,
        "9": Image9,
        "0": Image0,
        "q": ImageQ,
        "w": ImageW,
        "e": ImageE,
        "r": ImageR,
        "t": ImageT,
        "y": ImageY,
        "u": ImageU,
        "i": ImageI,
        "o": ImageO,
        "p": ImageP,
        "a": ImageA,
        "s": ImageS,
        "z": ImageZ,
        "shift": ImageShift,
        "ctrl": ImageCtrl,
        "alt": ImageAlt

    }
      
    const location = useLocation();
    const gameCtx = useContext(GameContext);
    const id = location.state?.id;
    const [game, setGame] = useState(null);
    const [gameField, setGameField] = useState(null);
    const [selectedPlayer, setSelectedPlayer] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [shootState, setShootState] = useState(false);
    const [validState, setValidState] = useState(false);
    const [gameTime, setGameTime] = useState(0);
    const Anonymous = {"id": 0, "name": "무명", "position": "NON", "recordResponseDto": {"gamePosition":"NON"}};
    const BluePlayer = {"id": -1, "name": "상대", "position": "NON", "recordResponseDto": {"gamePosition":"NON"}};
    const keyOrder = [1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 'q', 'w', 'e', 'r', 't', 'y', 'u', 'i', 'o', 'p']
    //최대, 최소시간 설정을 위한 minTime, maxTime
    const [minTime, setMinTime] = useState(0);
    const [maxTime, setMaxTime] = useState(120);
    //시간 설정 input 박스를 눌러서 숫자를 적을 때는 선수가 선택되지 않도록 하는 값
    const [inputFocusState, setInputFocusState] = useState(false);
    const navigate = useNavigate();


    //점과 선을 그리기 위한 캔버스
    const canvasRef = useRef(null);
    const ctx = canvasRef.current?.getContext('2d');
    const [drawLineState, setDrawLineState] = useState(false);
    const [create_dot_arr, setCreate_dot_arr] = useState([]);
    const [dot, setDot] = useState([]);

    //점 크기, 투명도, 색깔, 선의 크기 조절을 위한 radius, alpha, color, lineArrowWidth
    const [radius, setRadius] = useState(25);
    const [alpha, setAlpha] = useState(0.5);
    const [selectedColor, setSelectedColor] = useState('red');
    const [lineArrowWidth, setLineArrowWidth] = useState(5);

    //점 크기가 변경되면 새로 그리기 위해 다 지움
    //점과 선을 새로 그리는 메서드는 drawDotLineArrow
    const handleDotChange = (event) => {
        setRadius(event.target.value);
    };
    //선 크기가 변경되면 새로 그리기 위해 다 지움
    //점과 선을 새로 그리는 메서드는 drawDotLineArrow
    const handleLineArrowWidthChange = (event) => {
        setLineArrowWidth(event.target.value);

    };
    //투명도가 변경되면 새로 그리기 위해 다 지움
    const handleAlphaChange = (event) => {
        setAlpha((event.target.value)/100);
    };
    //name, position 이 변경되면 새로 그리기 위해 다 지움
    //handleNamePositionState 는 웹에 보여지는 name, position 선택원의 색을 변경하기 위한 state
    const [handleNamePositionState, setHandleNamePositionState] = useState(0);
    const handleNamePositionChange = (cnt) => {
        changeDotNameAndPositionState(cnt);
        setHandleNamePositionState(cnt);
    };

//<------------blue hide, red hide 버튼 눌러서 한쪽만 선택하기---------------->
    const [showBlue, setShowBlue] = useState(true);
    const [showRed, setShowRed] = useState(true);

    const handleBlueHide = () => {
        if(!showRed&&showBlue){setShowRed(!showRed);}
        setShowBlue(!showBlue);
    }
    const handleRedHide = () => {
        if(!showBlue&&showRed){setShowBlue(!showBlue);}
        setShowRed(!showRed);
    }
    const colorFilter = (dot) => {
        if(showBlue && showRed) {
            return dot;
        } else if(showBlue && !showRed) {
            const colorDot = dot.filter((colorDot) => {
                return colorDot.color === 'blue';
            });
            return colorDot;
        } else if(!showBlue && showRed) {
            const colorDot = dot.filter((colorDot) => {
                return colorDot.color === 'red';
            });
            return colorDot;
        } else {
            return null;
        }
    }

//<------------------blue , red 버튼 색 선택하기------------------------>
    const handleRedClick = () => {
        setSelectedColor('red');
        setSelectedPlayer(Anonymous);
      }
    
      const handleBlueClick = () => {
        setSelectedColor('blue');
        setSelectedPlayer(BluePlayer);
      }

      //점과 선을 새로 그리는 메서드, 점과 선 크기가 변경되면 지워지기 때문에 다시 그려줘야함
    const drawDotLineArrow = (newDot) => {
        newDot?.map((dot) => {
            dotDrawing(ctx,dot.x, dot.y, radius, dot.color, alpha, dot.playerName, dot.gamePosition, dot.playerId, dot.gameTime);
            lineDrawing(ctx, dot.beforeX, dot.beforeY, dot.shootX, dot.shootY, dot.color, dot.validShoot, dot.gameTime);
            arrowDrawing(ctx, dot.beforeX, dot.beforeY, dot.shootX, dot.shootY, dot.color, dot.gameTime);
        });
    }

 /*---------//canvas 에서 z 를 누른 상태로 정보 보기--------------*/
    // 가장 가까운 점의 정보를 저장합니다.
    const [nearestDot, setNearestDot] = useState(null); 
    const [showTooltip, setShowTooltip] = useState(false); // tooltip을 보여줄지 여부를 저장하는 상태값
    const [tooltipInfo, setTooltipInfo] = useState({}); // tooltip에 보여줄 정보를 저장하는 상태값

    // canvas에 마우스 이벤트를 추가합니다.
    const handleMouseMove = (event) => {
    const { clientX, clientY } = event;
    const rect = canvasRef.current.getBoundingClientRect(); // canvas의 위치 및 크기 정보를 가져옵니다.
    const x = clientX - rect.left; // canvas의 상대적인 x좌표를 계산합니다.
    const y = clientY - rect.top; // canvas의 상대적인 y좌표를 계산합니다.
    
    // 모든 점을 순회하면서 가장 가까운 점을 찾습니다.
    let nearest = null;
    if(dot  !== null){
        for (const nearDot of dot) {
        const distance = Math.sqrt((nearDot.x - x) ** 2 + (nearDot.y - y) ** 2); // 두 점 사이의 거리를 계산합니다.
        if (distance <= radius) { // 점의 반경 내에 마우스 위치가 있는 경우
                nearest = { nearDot }; // 가장 가까운 점을 업데이트합니다.
            }
        }
    }
    setNearestDot(nearest); // 가장 가까운 점의 정보를 업데이트합니다.
  }
  // canvas에 이벤트를 등록합니다.
    useEffect(() => {
        canvasRef.current.addEventListener('mousemove', handleMouseMove);
        // return () => canvasRef.current.removeEventListener('mousemove', handleMouseMove);
    }, [dot]);
  
  // nearestDot이 업데이트될 때마다 알림창을 띄웁니다.
    useEffect(() => {
        if (nearestDot) {
            let dotInfo = {};
            dotInfo.x = nearestDot.nearDot.x;
            dotInfo.y = nearestDot.nearDot.y;
            dotInfo.playerName = nearestDot.nearDot.playerName;
            dotInfo.gamePosition = nearestDot.nearDot.gamePosition;
            dotInfo.gameTime = nearestDot.nearDot.gameTime;
            dotInfo.shoot = nearestDot.nearDot.shoot;
            dotInfo.validShoot = nearestDot.nearDot.validShoot;
            setTooltipInfo(dotInfo);
        }
    }, [nearestDot]);

/*---------//canvas 에서 z 를 누른 상태로 정보 보면서 삭제하기--------------*/
    //z 를 누르면 선택한 점과 선을 지움
    const [deleteState, setDeleteState] = useState(false);
    const [deletedDot, setDeletedDot] = useState([]);
    const deleteClick = () => {
        dot.map((selectedDot, index) => {
            if(selectedDot.x == nearestDot?.nearDot.x && selectedDot.y == nearestDot?.nearDot.y){
                //deletedDot 에 저장하기
                setDeletedDot((prevDot) => [...prevDot, selectedDot]);
                const newDot = dot.slice(0, index).concat(dot.slice(index+1, dot.length));
                setDot(newDot);
            }
        })
    }

/*---------//canvas 에서 ctrl, shift 로 삭제 취소하기--------------*/
    const handleRedo = () => {
        if(deletedDot.length != 0){
            const newDot = deletedDot[deletedDot.length-1];
            setDot ((prevDot) => [...prevDot, newDot]);
            const newDeletedDot = deletedDot.slice(0, deletedDot.length - 1);
            setDeletedDot(newDeletedDot);
        }
    }
/*---------//canvas 에서 ctrl, z 로 최근 점 삭제하기--------------*/
    const handleUndo = () => {
        setDeletedDot((prevDot) => [...prevDot, dot[dot.length-1]]);
        const newDot = dot.slice(0, dot.length - 1)
        setDot(newDot);
    }

            



//---------------게임 정보 받아오기-----------------
    useEffect(() => {
        const fetchGame = async () => {
            setIsLoading(true);
            if(id){
                await gameCtx.getGame(id);
                await gameCtx.getGameField(id);
            }
            setSelectedPlayer(Anonymous);
            setGame(gameCtx.selectedGame);
            setGameField(gameCtx.gameField);
            setIsLoading(false);
          }
        fetchGame();
      }, []);

      useEffect(() => {
        setGame(gameCtx.selectedGame);
        setGameField(gameCtx.gameField);
      }, [gameCtx.gameField])
//<------------------변경되는 값에 따라 바로바로 렌더링---------------------->
      useEffect(() => {
        ctx?.clearRect(0, 0, canvasRef.current.width, canvasRef.current.height);
        drawDotLineArrow(colorFilter(dot));
      }, [gameTime, dot, selectedPlayer, handleNamePositionState, radius, alpha, lineArrowWidth, showBlue, showRed, maxTime, minTime, deletedDot])

    //버튼 클릭 시 해당 플레이어를 selectedPlayer에 저장
    //anonymous player 선택 시 id가 0이므로 따로 처리
    //색이 바뀌므로 새로 그려준다
    const handlePlayerButtonClick = (selectedId) => {
        setSelectedColor('red');
        if(selectedId == 0){
            setSelectedPlayer(Anonymous);
            setShowRed(true);
        }else if(selectedId == -1){
            setShowBlue(true);
            setSelectedColor('blue');
            setSelectedPlayer(BluePlayer);
        }else{
            setShowRed(true);
            game?.gamePlayerResponseDto.map((player) => {
                if (player.id == selectedId) {
                    setSelectedPlayer(player);
                }
            });
        }
    };
//<------------------------canvas에 점 그리기--------------------------->
    //점그리기 시작
    const handleClick = (event) => {
        //shoot 이 아닐 때는 점만 찍기
        const rect = canvasRef.current.getBoundingClientRect();
        const x = event.clientX - rect.left;
        const y = event.clientY - rect.top;
        if(!shootState){
            dotDrawing(ctx, x, y, radius, selectedColor, alpha, selectedPlayer.name, selectedPlayer.recordResponseDto.gamePosition, selectedPlayer.id, gameTime);
            setDot((prevDot) => [...prevDot, 
                {"x": x, "y": y, "color": selectedColor, "alpha": alpha, "playerId" : selectedPlayer.id, "playerName":selectedPlayer.name, "gamePosition" : selectedPlayer.recordResponseDto.gamePosition, "shoot": false, "validShoot": false, "shootX": null, "shootY" : null, "gameTime" : gameTime}]);
            }else{
            //shoot 일 때는 다음 클릭에 선도 그리기
            if(drawLineState){
                const beforeDot = create_dot_arr[0];
                const beforeX = beforeDot.x;
                const beforeY = beforeDot.y;
                lineDrawing(ctx, beforeX, beforeY, x, y, selectedColor, validState, gameTime);
                arrowDrawing(ctx, beforeX, beforeY, x, y, selectedColor, validState, gameTime);
                setDrawLineState(false);

                let lastDot = dot[dot.length - 1];
                lastDot.beforeX = beforeX;
                lastDot.beforeY = beforeY;
                lastDot.shootX = x;
                lastDot.shootY = y;
                lastDot.shoot= true;
                lastDot.validShoot = validState;
                setDot((prevDot) => [...prevDot.slice(0,-1), lastDot]);
                setCreate_dot_arr([]);
            }else{
                dotDrawing(ctx, x, y, radius, selectedColor, alpha, selectedPlayer.name, selectedPlayer.recordResponseDto.gamePosition, selectedPlayer.id, gameTime);
                setDot((prevDot) => [...prevDot, 
                    {"x": x, "y": y, "color": selectedColor, "alpha": alpha, "playerId" : selectedPlayer.id, "playerName":selectedPlayer.name, "gamePosition" : selectedPlayer.recordResponseDto.gamePosition, "shoot": false, "validShoot": false, "shootX": null, "shootY" : null, "gameTime" : gameTime}]);    
                let obj = {};
                obj.color = selectedColor;
                obj.x = x;
                obj.y = y;
                obj.radius = radius;
                setDrawLineState(true);
                setCreate_dot_arr((prevArr) => [...prevArr, { x: x, y: y}]);
                
            }
        }
        
    };

    //점 그리기
    const dotDrawing = (ctx, x, y, radius, color, dotAlpha, playerName, gamePosition, playerId, time) => {
        if(time <= maxTime && time >= minTime){
            ctx.save();
            ctx.beginPath();
            if(color === 'red'){
                if(playerId === selectedPlayer.id && playerId !== 0){
                    ctx.fillStyle = "rgba(0,255,0," + parseFloat(dotAlpha) + ")";
                }else{ctx.fillStyle = "rgba(255,0,0," + parseFloat(dotAlpha) + ")";}
            }else{
                ctx.fillStyle = "rgba(0,0,255," + parseFloat(dotAlpha) + ")";
            }
            ctx.strokeStyle = 'black'; // 테두리 색상 설정
            ctx.lineWidth = radius/10; // 테두리 두께 설정
            ctx.arc(x, y, radius, 0, Math.PI * 2, false);
            ctx.fill();
            ctx.stroke();
            ctx.fillStyle = "black";
            if(playerName !== "상대" && playerName !== "무명"){
                fillDotText(playerName, gamePosition, x, y);
            }
            ctx.restore();
        }
    }
    const [dotNameState, setDotNameState] = useState(false);
    const [dotPositionState, setDotPositionState] = useState(false);
    const fillDotText = (playerName, gamePosition, x, y) => {
        ctx.font = "bold " + radius/2 +"px Times New Roman";
        ctx.fillStyle = "black";
        if(dotNameState && dotPositionState){
            ctx.fillText(playerName, x- radius/2 - radius/10 * playerName.length, y);
            ctx.fillText(gamePosition, x - radius/6 * gamePosition.length, y + radius/2);
        }else if(dotNameState){
            ctx.fillText(playerName, x- radius/2 - radius/10 * playerName.length, y + radius /6);
        }else if(dotPositionState){
            ctx.fillText(gamePosition, x - radius/6 * gamePosition.length, y + radius /6);
        }
    }
    const changeDotNameAndPositionState = (state) => {
        if(state == 0){
            setDotNameState(false);
            setDotPositionState(false);
        }else if(state == 1){
            setDotNameState(true);
            setDotPositionState(false);
        }else if(state == 2){
            setDotNameState(false);
            setDotPositionState(true);
        }else if(state == 3){
            setDotNameState(true);
            setDotPositionState(true);
        }
    }

//<------------------------- 선 그리기 ---------------------------->
    const lineDrawing = (ctx, sx, sy, ex, ey, color, valid, time) => {
        if(time <= maxTime && time >= minTime){
            ctx.save();
            ctx.beginPath();
            ctx.strokeStyle = color;
            ctx.lineWidth = lineArrowWidth/2;
            if(!valid){
                ctx.setLineDash([10, 10]);
            }
            const dx = ex - sx;
            const dy = ey - sy;
            const distance = Math.sqrt(dx * dx + dy * dy);
            const ratio = (distance - radius) / distance;
            const ratio2 = (distance - lineArrowWidth) / distance;
            const nex = ex - dx * ratio;
            const ney = ey - dy * ratio;
            const nsx = sx + dx * ratio2;
            const nsy = sy + dy * ratio2;
            ctx.moveTo(nex, ney);
            ctx.lineTo(nsx, nsy);
            ctx.stroke();
            ctx.restore();
        }
    }
//<------------------------- 화살표 그리기 ---------------------------->
    const arrowDrawing = (ctx, sx, sy, ex, ey, color, time) => {
        if(time <= maxTime && time >= minTime){
            const aWidth = lineArrowWidth;
            const aLength = lineArrowWidth;
            const dx = ex - sx;
            const dy = ey - sy;
            const angle = Math.atan2(dy, dx);
            const length = Math.sqrt(dx * dx + dy * dy);

            //두점 선긋기
            ctx.translate(sx, sy);
            ctx.rotate(angle);
            ctx.fillStyle = color;
            ctx.beginPath();

            //화살표 모양 만들기
            ctx.moveTo(length - aLength, -aWidth);
            ctx.lineTo(length, 0);
            ctx.lineTo(length - aLength, aWidth);
            ctx.fill();
            ctx.setTransform(1, 0, 0, 1, 0, 0);
        }
    }

//<------------------------- Slider Range 에 따른 시간 설정 ---------------------------->
    const marks = {
        0: <strong>0`</strong>,
        45 : <strong>45`</strong>,
        90: <strong>90`</strong>,
        120: <strong>120`</strong>,
    };

    const RangeStyle = {
        width: '300px',
        paddingTop : '40px',
        height : '50px',
        margin: '0 50px',
    }

    const timeRangeHandler = (event) => {
        setMinTime(event[0]);
        setMaxTime(event[1]);
    }
    useEffect(() => {
    }, [gameTime])

    const gameTimeUpHandler = () => {
        setGameTime(prevState => {
            if(prevState < 120){
                return prevState + 1;
            }else{
                return prevState;}
        });
    }
    const gameTimeDownHandler = () => {
        setGameTime(prevState => {if(prevState > 0){return prevState - 1;}else{return prevState;}});
    }

    //inputbox focus 상태
    const handleFocus = () => {
        setInputFocusState(true);
      }
    
      const handleBlur = () => {
        setInputFocusState(false);
      }
  
//<------------------------- 이벤트 핸들러 ---------------------------->
    // Ctrl, Alt, 1~ 키를 누르는 이벤트 핸들러
    const handleKeyDown = (event) => {
        if(event.shiftKey && event.ctrlKey){
            handleRedo();
        }
        if(event.key === 'z' && event.ctrlKey){
            handleUndo();
        }else if(event.key === 'z'){
            setShowTooltip(true);
            setDeleteState(true);
        }else if(event.ctrlKey){
            setShootState(true);
        }
        if(event.altKey){
            setValidState(true);
        }
        if(!inputFocusState){
            switch (event.key) {
                case '1': handlePlayerButtonClick(0); break;
                case '2': handlePlayerButtonClick(game?.gamePlayerResponseDto[0]?.id); break;
                case '3': handlePlayerButtonClick(game?.gamePlayerResponseDto[1]?.id); break;
                case '4': handlePlayerButtonClick(game?.gamePlayerResponseDto[2]?.id); break;
                case '5': handlePlayerButtonClick(game?.gamePlayerResponseDto[3]?.id); break;
                case '6': handlePlayerButtonClick(game?.gamePlayerResponseDto[4]?.id); break;
                case '7': handlePlayerButtonClick(game?.gamePlayerResponseDto[5]?.id); break;
                case '8': handlePlayerButtonClick(game?.gamePlayerResponseDto[6]?.id); break;
                case '9': handlePlayerButtonClick(game?.gamePlayerResponseDto[7]?.id); break;
                case '0': handlePlayerButtonClick(game?.gamePlayerResponseDto[8]?.id); break;
                case 'q': handlePlayerButtonClick(game?.gamePlayerResponseDto[9]?.id); break;
                case 'w': handlePlayerButtonClick(game?.gamePlayerResponseDto[10]?.id); break;
                case 'e': handlePlayerButtonClick(game?.gamePlayerResponseDto[11]?.id); break;
                case 'r': handlePlayerButtonClick(game?.gamePlayerResponseDto[12]?.id); break;
                case 't': handlePlayerButtonClick(game?.gamePlayerResponseDto[13]?.id); break;
                case 'y': handlePlayerButtonClick(game?.gamePlayerResponseDto[14]?.id); break;
                case 'u': handlePlayerButtonClick(game?.gamePlayerResponseDto[15]?.id); break;
                case 'i': handlePlayerButtonClick(game?.gamePlayerResponseDto[16]?.id); break;
                case 'o': handlePlayerButtonClick(game?.gamePlayerResponseDto[17]?.id); break;
                case 'p': handlePlayerButtonClick(game?.gamePlayerResponseDto[18]?.id); break;
                case '\`': handlePlayerButtonClick(-1); break;
                default: break;
            }
          }
        switch (event.key) {
            case 'a':gameTimeDownHandler(); break;
            case 's':gameTimeUpHandler(); break;
            default: break;
          }
    }
    
    // Ctrl, Alt 키를 뗄 때 이벤트 핸들러
    const handleKeyUp = (event) => {
        if (!event.ctrlKey) {
            setDrawLineState(false);
            setShootState(false);
            setCreate_dot_arr([]);
        }
        if(!event.altKey){
            setValidState(false);
        }
        if(event.key === 'z'){
            setDeleteState(false);
            setShowTooltip(false)
        }
    }

    useEffect(() => {
        // 키 이벤트 리스너 등록
        window.addEventListener("keydown", handleKeyDown);
        window.addEventListener("keyup", handleKeyUp);
      
        // 컴포넌트 언마운트 시 이벤트 리스너 제거
        return () => {
          window.removeEventListener("keydown", handleKeyDown);
          window.removeEventListener("keyup", handleKeyUp);
        };
      }, [dot]);


      //createGameField 호출
        const handleCreateGameField = async () => {
            const gameId = game.id;
            const dotRecordRequestDto = dot;
            if(window.confirm('저장하시겠습니까?')){
                setIsLoading(true);
                gameCtx.createGameField(gameId, dotRecordRequestDto)
                window.alert('저장되었습니다.');
                setIsLoading(false);
            }
        }

        //기존에 저장된 정보를 불러오는 함수
        //한 번 불러오면 다시는 못 불러오게 함
        const [downloadState, setDownloadState] = useState(false);

        const download = () => {
            if(window.confirm('저장된 게임 정보를 불러오시겠습니까?')){
                if(downloadState === true){
                    alert('이미 불러왔습니다.');
                    return;
                }else{
                    savedDot();
                    setDownloadState(true);
                }
            }
        }

        const savedDot = () => {
            let newDot = [];
            
            gameField.dotRecordResponseDto?.map((givenDot) => {
                const givenColor = givenDot.playerId === -1 ? "blue" : "red";
                newDot.push({"x": givenDot.x, "y": givenDot.y, "color": givenColor, "alpha": alpha, "playerId" : givenDot.playerId, "playerName":givenDot.playerName, "gamePosition" : selectedPlayer.recordResponseDto.gamePosition, "shoot": false, "validShoot": false, "shootX": null, "shootY" : null, "gameTime" : givenDot.gameTime})
                setDot((prevDot) => [...prevDot, 
                    {"x": givenDot.x, "y": givenDot.y, "color": givenColor, "alpha": alpha, "playerId" : givenDot.playerId, "playerName":givenDot.playerName, "gamePosition" : selectedPlayer.recordResponseDto.gamePosition, "shoot": false, "validShoot": false, "shootX": null, "shootY" : null, "gameTime" : givenDot.gameTime
                }]);             
                })
        }

//------------------뒤로가기------------------
    const handleBack = () => {
        if(window.confirm('뒤로가시겠습니까?')) 
        navigate(`/games/detail`, { state: { id: game.id } });
    }

            
    return (
    <div>
        <h2 class='title'>Hit Map</h2>
        <div className={classes['box-container']}>
            <div style={{width: "150px"}}>
                <input type="range" min="0" max="50" value={radius} onChange={handleDotChange}/>
                <input type="range" min="0" max="50" value={lineArrowWidth} onChange={handleLineArrowWidthChange}/>
                <input type="range" min="0" max="100" value={alpha*100} onChange={handleAlphaChange}/>
            </div>
            <ButtonGroup style={{display:'flex', alignItems:'center'}}>
                <ToggleButton key='1' id='radio-red'  type="radio" variant='danger' name="radio" 
                onClick={()=> {handleRedClick(); setShowRed(true)}} checked={selectedColor == 'red'? true:false} style={{width:"70px", textAlign: 'center'}}> Red
                </ToggleButton>
                <ToggleButton key='2' id='radio-red'  type="radio" variant='primary' name="radio" 
                onClick={()=> {handleBlueClick(); setShowBlue(true)}} checked={selectedColor == 'blue'? true:false} style={{width:"70px"}}> Blue
                </ToggleButton>
            </ButtonGroup>
            <div className={classnames(classes['displayed-dot-black'], handleNamePositionState === 3 && classes['displayed-dot-select'])} onClick={()=>handleNamePositionChange(3)}>Name{'\n'}Position</div>
            <div className={classnames(classes['displayed-dot-black'], handleNamePositionState === 1 && classes['displayed-dot-select'])} onClick={()=>handleNamePositionChange(1)}>Name</div>
            <div className={classnames(classes['displayed-dot-black'], handleNamePositionState === 2 && classes['displayed-dot-select'])} onClick={()=>handleNamePositionChange(2)}>Position</div>
            <div className={classnames(classes['displayed-dot-black'], handleNamePositionState === 0 && classes['displayed-dot-select'])} onClick={()=>handleNamePositionChange(0)}/>
            <div className={classnames(classes['displayed-dot-hide-red'], !showRed && classes['displayed-dot-hided'])} onClick={()=>{handleRedHide(); handleBlueClick()}}/>
            <div className={classnames(classes['displayed-dot-hide-blue'], !showBlue && classes['displayed-dot-hided'])} onClick={()=>{handleBlueHide(); handleRedClick()}}/>
            <Slider range min={0} max={120} marks={marks} aria-labelledby="continuous-slider" defaultValue={[0, 120]} pushable={true}
            onChange={timeRangeHandler}  style={RangeStyle} handleRender={handleRender} 
                trackStyle={{ backgroundColor: 'rgb(136, 99, 195)', height: 5 }}
                handleStyle={{
                    borderColor: 'rgb(136, 99, 195)',
                    height: 20,
                    width: 20,
                    marginTop: -9,
                  }}
            />
            <input class='hitmap-input' type='number' value={gameTime} onChange={(event)=>setGameTime(parseInt(event.target.value))} style={{width: "50px"}}/>
        </div>
            <div className={classes['time-container']}>
            <div></div>
            <span>
                <Button onClick={handleBack}>뒤로가기</Button>
                <Button onClick={download}>불러오기</Button>
                <Button onClick={handleCreateGameField}>저장하기</Button>
            </span>
        </div>
        <div style={{ display: 'flex', flexDirection: 'row' }}>
            <div className={classes['parent']}>
                <canvas
                    ref={canvasRef}
                    className={classes['canvas']}
                    onMouseDown={deleteState ? deleteClick : handleClick}
                    width="1300px"
                    height="860px"
                />
                <img src={soccerField} alt="soccer field" draggable={false}/>
                {showTooltip && (
                <div class='hitmap-toolTip' style={{ position: "absolute", left: tooltipInfo?.x + 10, top: tooltipInfo?.y + 10}}
                    >
                    {tooltipInfo?.shoot && <div>{tooltipInfo.validShoot ? "유효슛" : "슛"}<br/></div>}
                    {tooltipInfo?.playerName}<br/>
                    {tooltipInfo?.gamePosition}<br/>
                    {tooltipInfo?.gameTime}`<br/>
                </div>
                )}
            </div>
            <div className={classes['button-container']}>
                    <img class='hitmap-img' src={Images['-1']} alt="상대팀" style={selectedPlayer?.id === -1 ? {opacity:'0.5'} : null}/>
                    <Button id='-1' onClick={()=>{handlePlayerButtonClick('-1'); setSelectedColor('blue')}}
                    className={ selectedPlayer?.id == -1 ? classes['selected'] : ""}>상대팀</Button>
                    
                    <br/>
                    <img class='hitmap-img' src={Images['1']} alt="무명" style={selectedPlayer?.id === 0 ? {opacity:'0.5'} : null}/>
                    <Button id='0' onClick={()=>{handlePlayerButtonClick('0'); setSelectedColor('red')}}
                    className={ selectedPlayer?.id == 0 ? classes['selected'] : ""}>무 명</Button>
                    
                    {game?.gamePlayerResponseDto.map((player, index) => (
                    <><br/><img class='hitmap-img' src={Images[index+2]} alt={index+2} style={selectedPlayer?.id === player.id ? {opacity:'0.5'} : null}/>
                    <Button key={player.id} id={player.id} 
                    className={ selectedPlayer?.id === player.id ? classes['selected'] : ""} 
                    onClick={()=>handlePlayerButtonClick(player.id)}>{player.name}</Button></>))}
            </div>
        </div>
        </div>
        
    );
}

export default GameHitMap;