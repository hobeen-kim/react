import React, { Fragment } from "react";
import MainNavigation from "./MainNavigation";
import Footer from "./Footer";
import AuthContext from '../../store/auth/auth-context';
import {useContext, useState, useEffect } from "react";

const Layout = (props) => {

  const authCtx = useContext(AuthContext);
  const [isLoggined, setIsLoggined] = useState(false);

  const toggleLogoutHandler = () => {
    authCtx.logout();
  }

  useEffect(() => {
    if (authCtx.isLoggedIn) {
        setIsLoggined(true);
    }
  }, [authCtx.accessToken]);

  return (
    <Fragment>
      <div style={{position: 'fixed', width: '100%', zIndex: '10'}}>
        <MainNavigation/>
      </div>
      <main style={{marginTop: '6rem', minHeight: '100vh', backgroundColor:'black'}}>{props.children}</main> {/* Added padding to ensure content doesn't get hidden by footer */}
      <div style={{position: 'relative', bottom: 0, width: '100%', zIndex: 10}}>
        <Footer/>
      </div>

    </Fragment>


      
  )
};

export default Layout;
