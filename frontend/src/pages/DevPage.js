import { Fragment } from "react";
import React from "react";
import { Routes, Route } from "react-router-dom";
import {DevContextProvider} from "../store/dev/dev-context";
import DevLogging from "../component/dev/DevLogging";
import AuthContext from "../store/auth/auth-context";
import {useContext } from "react";

const DevPage = () => {

    const authCtx = useContext(AuthContext);
    

    return (
      <Fragment>
          <DevContextProvider>
            <Routes>
                <Route path="/log" element={<DevLogging />} />
            </Routes>
          </DevContextProvider>
      </Fragment>
    );
  };
  
  export default DevPage;