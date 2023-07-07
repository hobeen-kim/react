import { useContext, useEffect, useState } from 'react';

import AuthContext from '../../store/auth/auth-context';
import React from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import Container from 'react-bootstrap/Container';
import Nav from 'react-bootstrap/Nav';
import Navbar from 'react-bootstrap/Navbar';
import { useNavigate } from 'react-router-dom';


const MainNavigation = () =>{

  const authCtx = useContext(AuthContext);
  const navigate = useNavigate();

  const toggleLogoutHandler = () => {
    authCtx.logout();
    navigate('/');
  }

  return(
      <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" style={{height:"100px"}}>
      <Container style={{marginLeft:"300px", height:"70px", fontSize:"20px"}}>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          <Nav className="me-auto">
            <Nav.Link href="/hitmap" style={{marginRight:"50px"}}>히트맵 작성</Nav.Link>
            <Nav.Link href="/formation" style={{marginRight:"50px"}}>포메이션</Nav.Link>
            <Nav.Link href="/players" style={{marginRight:"50px"}}>선수 관리</Nav.Link>
            <Nav.Link href="/games" style={{marginRight:"50px"}}>경기 관리</Nav.Link>
            <Nav.Link href="/posts" style={{marginRight:"50px"}}>커뮤니티</Nav.Link>
          </Nav>
          <Nav>
            {
              authCtx.isLoggedIn && 
              <>
              <Nav.Link href="/profile">프로필</Nav.Link>
              <Nav.Link eventKey={2} onClick={toggleLogoutHandler}>logout</Nav.Link>
              </>
            }
            {
              !authCtx.isLoggedIn &&
              <>
              <Nav.Link href="/auth/login">로그인</Nav.Link>
              <Nav.Link href="/auth/signup">회원가입</Nav.Link>
              </>
            }
            
            
          </Nav>
        </Navbar.Collapse>
      </Container>
    </Navbar>    
  );
};

export default MainNavigation;