import { useContext, useEffect, useState } from 'react';

import AuthContext from '../../store/auth/auth-context';
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';

const MainNavigation = () =>{

  const authCtx = useContext(AuthContext);
  const [nickname, setNickname] = useState('');
  let isLogin = authCtx.isLoggedIn;
  let isGet = authCtx.isGetSuccess;

  const callback = (str) => {
    setNickname(str);
  }

  useEffect(() => {
    if (isLogin) {
      authCtx.getUser();
    } 
  }, [isLogin]);

  useEffect(() => {
    if (isGet) {
      callback(authCtx.userObj.nickname);
    }
  }, [isGet]);


  const toggleLogoutHandler = () => {
    authCtx.logout();
  }

  return(
    isLogin && 
      <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" style={{height:"100px"}}>
      <Container style={{marginLeft:"300px", width: '3000px !important', height:"70px", fontSize:"20px"}}>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/players" style={{marginRight:"50px"}}>선수 관리</Nav.Link>
            <Nav.Link href="/games" style={{marginRight:"50px"}}>경기 관리</Nav.Link>
            <Nav.Link href="/community" style={{marginRight:"50px"}}>커뮤니티</Nav.Link>
          </Nav>
          <Nav>
            <Nav.Link href="/profile">{nickname}</Nav.Link>
            <Nav.Link eventKey={2} onClick={toggleLogoutHandler}>
              logout
            </Nav.Link>
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>    
  );
};

export default MainNavigation;