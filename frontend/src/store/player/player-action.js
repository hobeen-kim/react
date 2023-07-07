import {GET, POST, PUT, DELETE} from "../auth/fetch-action";

const createTokenHeader = (token) => {
    return {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    }
  }

export const getPlayersActionHandler = (token) => {
    const URL = '/players';
    const response = GET(URL, createTokenHeader(token));
    return response;
}

export const getPlayerActionHandler = (token, playerId) => {
    const URL = '/players/' + playerId;
    const response = GET(URL, createTokenHeader(token));
    return response;
}

export const createPlayerActionHandler = (token, name, position) => {
    const URL = '/players';
    const playerObject = {name, position};
    const response = POST(URL, playerObject, createTokenHeader(token));
    return response;
}

export const updatePlayerActionHandler = (token, playerId, name, position) => {
    const URL = '/players/' + playerId;
    const playerObject = {"name":name, "position":position};
    const response = PUT(URL, playerObject, createTokenHeader(token));
    return response;
}

export const deletePlayerActionHandler = (token, playerId) => {
    const URL = '/players/' + playerId;
    const response = DELETE(URL, createTokenHeader(token));
    return response;
}