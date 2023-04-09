import React, { createContext, useState } from 'react';
import { getPlayersActionHandler, createPlayerActionHandler, updatePlayerActionHandler, deletePlayerActionHandler, getPlayerActionHandler } from './player-action';
import * as authAction from '../auth/auth-action'; 

export const PlayerContext = createContext({
  players: [],
  selectedPlayer: null,
  error: null,
  positions: [],
  recordName: [],
  recordNameInteg : [],
  getPlayers: () => {},
  createPlayer: async (name, position) => {},
  updatePlayer: async (playerId, name, position) => {},
  deletePlayer: async (playerId) => {},
  getSelectedPlayer: async (playerId) => {}
});

export const PlayerContextProvider = ({ children }) => {

  const tokenData = authAction.retrieveStoredToken();
  let initialToken;
  if (tokenData) {
    initialToken = tokenData.token;
  }
  const [token, setToken] = useState(initialToken);
  const [players, setPlayers] = useState([]);
  const [selectedPlayer, setSelectedPlayer] = useState(null);
  const [error, setError] = useState(null);
  const [positions, setPositions] = useState(['GK','RB','RCB','LCB','LB','CB','RM','RCM','LCM','LM','CDM','RS','LS','CF']);
  const [recordName, setRecordName] = useState([
    '터치', '골','어시스트','찬스메이킹','슛','유효슛','드리블','성공','패스',
    '성공','롱패스','성공','크로스','성공','태클','인터셉트',
    '경합','성공','턴오버']);
  const [recordNameInteg, setRecordNameInteg] = useState([
    '터치', '골','어시스트','찬스메이킹','슛(유효슛)','드리블(성공)',
    '패스(성공)','롱패스(성공)','크로스(성공)',
    '태클','인터셉트','경합(성공)','턴오버']);

  const getPlayers = async () => {
    setError(null);
    try {
      const response = await getPlayersActionHandler(token);
      setPlayers(response.data);
    } catch (error) {
      setError(error.message || 'Something went wrong!');
    }
  };

  const createPlayer = async (name, position) => {
    setError(null);
    try {
      await createPlayerActionHandler(token, name, position);
      await getPlayers(token);
    } catch (error) {
      setError(error.message || 'Something went wrong!');
    }
  };

  const updatePlayer = async (playerId, name, position) => {
    setError(null);
    try {
      await updatePlayerActionHandler(token, playerId, name, position);
      await getPlayers(token);
    } catch (error) {
      setError(error.message || 'Something went wrong!');
    }
  };

  const deletePlayer = async (playerId) => {
    setError(null);
    try {
      await deletePlayerActionHandler(token, playerId);
      await getPlayers(token);
    } catch (error) {
      setError(error.message || 'Something went wrong!');
    }
  };

  const getSelectedPlayer = async (playerId) => {
    setError(null);
    try {
      const response = await getPlayerActionHandler(token, playerId);
      setSelectedPlayer(response.data);
    } catch (error) {
      setError(error.message || 'Something went wrong!');
    }
  };

  const contextValue ={
    players,
    selectedPlayer,
    error,
    positions,
    recordName,
    recordNameInteg,
    getPlayers,
    createPlayer,
    updatePlayer,
    deletePlayer,
    getSelectedPlayer
  }


  return (
    <PlayerContext.Provider value={contextValue}>
      {children}
    </PlayerContext.Provider>
  );
};
