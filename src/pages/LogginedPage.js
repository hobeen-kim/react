
import { Fragment } from "react";
import React from "react";
import { Routes, Route } from "react-router-dom";
import "../public/css/logginedPage.css"
import Loggined from "../component/auth/Loggined";
import PlayerPage from "./PlayerPage";
import GamePage from "./GamePage";
import CommunityPage from "./CommunityPage";
import ProfilePage from "./ProfilePage";
const LogginedPage = () => {

  return (
  <Fragment>
    <div class='logginedPage-background'>
    <Routes>
      <Route path="/" element={<Loggined />} />
      <Route path="/players" element={<PlayerPage />} />
      <Route path="/games/*" element={<GamePage />} />
      <Route path="/community/*" element={<CommunityPage />} />
      <Route path="/profile/*" element={<ProfilePage />} />
    </Routes>
    </div>

  </Fragment>
  );};

export default LogginedPage;