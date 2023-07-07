import React, { createContext, useState } from 'react';
import * as authAction from '../auth/auth-action'; 
import { getGamesActionHandler, createGameActionHandler, updateGameActionHandler, deleteGameActionHandler,
     getGameActionHandler, addPlayerActionHandler, updatePlayerActionHandler, deletePlayerActionHandler, 
     getGameFieldActionhandler, createGameFieldActionHandler } from './game-action';

export const GameContext = createContext({
    games: [],
    selectedGame: null,
    selectedPlayerRecord: null,
    error: null,
    positions : [],
    recordName : [],
    recordNameInteg : [],
    createInit : [],
    gameField : [],
    getGames: async () => {},
    getGame: async (gameId) => {},
    createInitGame: async () => {},
    createGame: (gameName, opponent, location, GF, GA, createdAt, players) => {},
    updateGame: async (gameId, gameName, opponent, location, GF, GA, createdAt) => {},
    deleteGame: async (gameId) => {},
    addPlayer: async (gameId, playerId, gamePosition) => {},
    updatePlayer : async (gameId, playerId, timeIn, timeOut, gamePosition, main, touch, goal, assist, chanceMaking,
        shoot, validShoot, dribble, successDribble, pass, successPass, longPass, successLongPass,
        crossPass, successCrossPass, tackle, intercept, contention, successContention, turnover) => {},
    deletePlayer : async (gameId, playerId) => {},
    getGameField : async (gameId) => {},
    createGameField : async (gameId, dotRecords) => {}
});

export const GameContextProvider = ({ children }) => {

    const tokenData = authAction.retrieveStoredToken();
    let initialToken;
    if (tokenData) {
      initialToken = tokenData.accessToken;
    }
    const [token, setToken] = useState(initialToken);
    const [games, setGames] = useState([]);
    const [selectedGame, setSelectedGame] = useState(null);
    const [selectedPlayerRecord, setSelectedPlayerRecord] = useState(null);
    const [createInit, setCreateInit] = useState([]);
    const [error, setError] = useState(null);
    const [message, setMessage] = useState(null);
    const [positions, setPositions] = useState(['GK','RB','RCB','LCB','LB','CB','RM','RCM','LCM','LM','CDM','RS','LS','CF']);
    const [recordName, setRecordName] = useState([
        '터치', '골','어시스트','찬스메이킹','슛','유효슛','드리블','성공','패스',
        '성공','롱패스','성공','크로스','성공','태클','인터셉트',
        '경합','성공','턴오버']);
      const [recordNameInteg, setRecordNameInteg] = useState([
        '터치', '골','어시스트','찬스메이킹','슛(유효슛)','드리블(성공)',
        '패스(성공)','롱패스(성공)','크로스(성공)',
        '태클','인터셉트','경합(성공)','턴오버']);
    const [gameField, setGameField] = useState([]);
        
    const getGames = async () => {
        setError(null);
        try {
            setSelectedGame(null);
            const response = await getGamesActionHandler(token);
            setGames(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const createGame = async (gameName, opponent, location, GF, GA, createdAt, players) => {
        setError(null);
        try {
            const response = await createGameActionHandler(token, gameName, opponent, location, GF, GA, createdAt, players);
            setSelectedGame(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const updateGame = async (gameId, gameName, opponent, location, GF, GA, createdAt) => {
        setError(null);
        try {
            const response = await updateGameActionHandler(token, gameId, gameName, opponent, location, GF, GA, createdAt);
            setSelectedGame(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const deleteGame = async (gameId) => {
        setError(null);
        try {
            const response = await deleteGameActionHandler(token, gameId);
            setMessage(response.data.message);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const getGame = async (gameId) => {
        setError(null);
        try {
            const response = await getGameActionHandler(token, gameId);
            setSelectedGame(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const addPlayer = async (gameId, playerId, gamePosition, main) => {
        setError(null);
        try {
            await addPlayerActionHandler(token, gameId, playerId, gamePosition, main);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const updatePlayer = async (gameId, playerId, timeIn, timeOut, gamePosition, main, touch, goal, assist, chanceMaking,
        shoot, validShoot, dribble, successDribble, pass, successPass, longPass, successLongPass,
        crossPass, successCrossPass, tackle, intercept, contention, successContention, turnover) => {
        setError(null);
        try {
            const response = await updatePlayerActionHandler(token, gameId, playerId, timeIn, timeOut, gamePosition, main, touch, goal, assist, chanceMaking,
                shoot, validShoot, dribble, successDribble, pass, successPass, longPass, successLongPass,
                crossPass, successCrossPass, tackle, intercept, contention, successContention, turnover);
                setSelectedPlayerRecord(response.data);
            } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const deletePlayer = async (gameId, playerId) => {
        setError(null);
        try {
            const response = await deletePlayerActionHandler(token, gameId, playerId);
            setSelectedGame(response.data);

        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const getGameField = async (gameId) => {
        setError(null);
        try {
            const response = await getGameFieldActionhandler(token, gameId);
            setGameField(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const createGameField = async (gameId, dotRecords) => {
        setError(null);
        try {
            const response = await createGameFieldActionHandler(token, gameId, dotRecords);
            setSelectedGame(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }

    const contextValue = {
        games,
        selectedGame,
        selectedPlayerRecord,
        positions,
        recordName,
        recordNameInteg,
        error,
        createInit,
        gameField,
        getGames,
        createGame,
        updateGame,
        deleteGame,
        getGame,
        addPlayer,
        updatePlayer,
        deletePlayer,
        getGameField,
        createGameField
    }

    return (
        <GameContext.Provider value={contextValue}>
            {children}
        </GameContext.Provider>
    );
}

