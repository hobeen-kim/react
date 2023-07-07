import { useContext, useEffect, useState } from 'react';

import AuthContext from '../../store/auth/auth-context';
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import '../../public/css/footer.css'
import logo from "../../public/image/logo.png";
import logoText from "../../public/image/logo-text.png";




const Footer = () =>{

  const authCtx = useContext(AuthContext);

  const toggleLogoutHandler = () => {
    authCtx.logout();
  }

  return(
    <div class="p-3 bg-dark footer">
        <img src={logo} className="footer-logo-img" alt="logo" />
        <img src={logoText} className="footer-logo-text" alt="logo" />
        <br/>
        문의 : sksjsksh32@naver.com
    </div>
  );
};

export default Footer;