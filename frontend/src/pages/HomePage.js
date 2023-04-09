import StartingPage from "../component/auth/StartingPage";
import AuthForm from "../component/auth/AuthForm";
import SignUp from "../component/auth/signUp";
import Logined from "../component/auth/Loggined";
import { Fragment } from "react";
import React from "react";
import { Routes, Route, Navigate } from "react-router-dom";
import AuthContext from "../store/auth/auth-context";
import {useContext, useRef, useEffect } from "react";
import "../public/css/homePage.css"

const HomePage = () => {

  const authCtx = useContext(AuthContext);

  return (
  <Fragment>
    <Routes>
      <Route path="/" element={!authCtx.isLoggedIn?<StartingPage /> : <Logined/>} />
      <Route path="/signup" element={authCtx.isLoggedIn ? <Navigate to='/' /> : <SignUp />} />
      <Route path="/login" element={authCtx.isLoggedIn ? <Navigate to='/' /> : <AuthForm />} />
    </Routes>
  </Fragment>
  );};

export default HomePage;