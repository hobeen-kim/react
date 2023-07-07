
import { Fragment } from "react";
import React from "react";
import { Routes, Route } from "react-router-dom";
import "../public/css/logginedPage.css"
import Loggined from "../component/auth/Loggined";
import PlayerPage from "./PlayerPage";
import GamePage from "./GamePage";
import PostPage from "./PostPage";
import CreateGameField from "../component/game/GameHitMap";
import Formation from "../component/formation/Formation";
import AuthContext from "../store/auth/auth-context";
import {useContext } from "react";
import { Navigate } from 'react-router-dom';
import AuthPage from "./AuthPage";
import "../public/css/homePage.css"

const LogginedPage = () => {

  const authCtx = useContext(AuthContext);

  return (
  <Fragment>
    <div>
    <Routes>
      <Route path="/" element={<Loggined />} />
      <Route path="/players" element={<PlayerPage />} />
      <Route path="/games/*" element={<GamePage />} />
      <Route path="/hitmap" element={<CreateGameField />} />
      <Route path="/formation" element={<Formation />} />
      <Route path="/posts/*" element={<PostPage />} />
      <Route path="/auth/*" element={authCtx.isLoggedIn ? <Navigate to="/" /> : <AuthPage />} />
    </Routes>
    </div>

  </Fragment>
  );};

export default LogginedPage;