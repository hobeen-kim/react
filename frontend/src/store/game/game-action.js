import {GET, POST, PUT, DELETE} from "../auth/fetch-action";

const createTokenHeader = (token) => {
    return {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    }
  }

export const getGamesActionHandler = (token) => {
    const URL = '/game/list';
    const response = GET(URL, createTokenHeader(token));
    return response;
}

export const getGameActionHandler = (token, gameId) => {
    const URL = '/game/' + gameId;
    const response = GET(URL, createTokenHeader(token));
    return response;
}

export const createGameActionHandler = (token, gameName, opponent, location, GF, GA, createdAt, players) => {
    const URL = '/game/create';
    const gameObject = {"gameName":gameName, "opponent":opponent, "location":location, "gf":GF, "ga":GA,
                        "createdAt": createdAt, "gamePlayerAddRequestDto":players};
    const response = POST(URL, gameObject, createTokenHeader(token));
    return response;
}

export const createGameInitActionHandler = (token) => {
    const URL = '/game/create';
    const response = GET(URL, createTokenHeader(token));
    return response;
}

export const updateGameActionHandler = (token, gameId, gameName, opponent, location, GF, GA, createdAt) => {
    const URL = '/game/update';
    const gameObject = {"id":gameId, "gameName":gameName, "opponent":opponent, "location":location, "gf":GF, "ga":GA, "createdAt":createdAt};
    const response = PUT(URL, gameObject, createTokenHeader(token));
    return response;
}

export const deleteGameActionHandler = (token, gameId) => {
    const URL = '/game/delete?id=' + gameId;
    const response = DELETE(URL, createTokenHeader(token));
    return response;
}

export const addPlayerActionHandler = (token, gameId, playerId, gamePosition, main) => {
    const URL = '/game/addPlayer?id=' + gameId;
    const gameObject = {"playerId":playerId, "gamePosition":gamePosition, "main":main};
    const response = POST(URL, gameObject, createTokenHeader(token));
    return response;
}

export const updatePlayerActionHandler = (
    token, gameId, playerId, timeIn, timeOut, gamePosition, main, touch, goal, assist, chanceMaking, 
    shoot, validShoot, dribble, successDribble, pass, successPass, longPass, successLongPass,
    crossPass, successCrossPass, tackle, intercept, contention, successContention, turnover) => {
    const URL = '/game/updatePlayer?id=' + gameId;
    const gameObject = {
        "id":playerId, "timeIn":timeIn, "timeOut":timeOut, "gamePosition":gamePosition, "main":main, "touch":touch, "goal":goal, "assist":assist, "chanceMaking":chanceMaking, 
        "shoot":shoot, "validShoot":validShoot, "dribble":dribble, "successDribble":successDribble, "pass":pass, "successPass":successPass, "longPass":longPass, "successLongPass":successLongPass,
        "crossPass":crossPass, "successCrossPass":successCrossPass, "tackle":tackle, "intercept":intercept, "contention":contention, "successContention":successContention, "turnover":turnover
        };
    const response = PUT(URL, gameObject, createTokenHeader(token));
    return response;
}

export const deletePlayerActionHandler = (token, gameId, playerId) => {
    const URL = '/game/deletePlayer?id=' + gameId + '&playerId=' + playerId;
    const response = DELETE(URL, createTokenHeader(token));
    return response;
}

export const getGameFieldActionhandler = (token, gameId) => {
    const URL = '/game/gameField?id=' + gameId;
    const response = GET(URL, createTokenHeader(token));
    return response;
}

export const createGameFieldActionHandler = (token, gameId, dotRecords) => {
    const URL = '/game/createGameField';
    const gameObject = {"id" : gameId, "dotRecordRequestDto":dotRecords};
    const response = POST(URL, gameObject, createTokenHeader(token));
    return response;
}
