import { Fragment } from "react";
import React from "react";
import { Routes, Route } from "react-router-dom";
import "../public/css/logginedPage.css"
import AuthContext from "../store/auth/auth-context";
import {useContext, useState, useEffect } from "react";
import AuthForm from "../component/auth/AuthForm";
import SignUp from "../component/auth/signUp";
import StartingPage from "../component/auth/StartingPage";

const AuthPage = () => {

  const authCtx = useContext(AuthContext);

  const [isLoggined, setIsLoggined] = useState(false);

    useEffect(() => {
        if (authCtx.isLoggedIn) {
            setIsLoggined(true);
        }
    }, [authCtx.isLoggedIn]);


  return (
  <Fragment>
    <div>
    <Routes>
      <Route path="/login" element={<AuthForm />} />
      <Route path="/signup" element={<SignUp />} />
      <Route path="/" element={<StartingPage />} />
    </Routes>
    </div>

  </Fragment>
  );};

export default AuthPage;