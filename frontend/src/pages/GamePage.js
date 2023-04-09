import { Fragment } from "react";
import React from "react";
import { Routes, Route } from "react-router-dom";
import {GameContextProvider} from "../store/game/game-context";
import GameList from "../component/game/GameList";
import GameDetail from "../component/game/GameDetail";
import GameCreate from "../component/game/CreateGame";
import CreateGameField from "../component/game/GameHitMap";
import '../public/css/gamePage.css';


const GamePage = () => {

    return (
      <Fragment>
          <GameContextProvider>
          <Routes>
          <Route path="/" element={<GameList />} />
          <Route path="/detail" element={<GameDetail />} />
          <Route path="/create" element={<GameCreate />} />
          <Route path="/hitmap" element={<CreateGameField />} />
        </Routes>
          </GameContextProvider>
      </Fragment>
    );
  };
  
  export default GamePage;