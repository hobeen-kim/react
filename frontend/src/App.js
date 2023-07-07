import React, { useContext, useEffect } from 'react';
import { Routes, Route, Navigate, Link } from 'react-router-dom';
import Layout from './component/layout/Layout';
import AuthContext from './store/auth/auth-context';
import './public/css/app.css';
import logo from "./public/image/logo.png";
import logoText from "./public/image/logo-text.png";
import LogginedPage from './pages/LogginedPage';


function App() {

  const authCtx = useContext(AuthContext);

  // const useUnload = (callback) => {
  //   useEffect(() => {
  //     const handleUnload = (e) => {
  //       e.preventDefault();
  //       e.returnValue = "";
  //       callback();
  //     };
  
  //     window.addEventListener("unload", handleUnload);
  
  //     return () => {
  //       window.removeEventListener("unload", handleUnload);
  //     };
  //   }, [callback]);
  // };

  // const usePreventUnload = () => {
  //   useEffect(() => {
  //     const handleMouseLeave = () => {
  //       window.preventUnload = true;
  //     };
  
  //     const handleMouseEnter = () => {
  //       window.preventUnload = false;
  //     };
  
  //     const handleKeyDown = (e) => {
  //       if (e.keyCode === 91 || e.keyCode === 18) {
  //         window.preventUnload = false; // 단축키 ALT+TAB (창 변경)
  //       }
  //       if (e.keyCode === 116 || (e.ctrlKey && e.keyCode === 82)) {
  //         window.preventUnload = false; // 단축키 F5, CTRL+F5, CTRL+R (새로고침)
  //       }
  //     };
  
  //     const handleClick = (e) => {
  //       const href = e.target.getAttribute("href");
  //       const target = e.target.getAttribute("target");
  //       const form = e.target.closest("form");
  
  //       if (href !== null || target !== null || form !== null) {
  //         window.preventUnload = false; // a 링크, 버튼 클릭 등
  //       } else {
  //         window.preventUnload = true; // 다른 페이지로 이동하는 경우
  //       }
  //     };
  
  //     document.addEventListener("mouseleave", handleMouseLeave);
  //     document.addEventListener("mouseenter", handleMouseEnter);
  //     document.addEventListener("keydown", handleKeyDown);
  //     document.addEventListener("click", handleClick);
  
  //     return () => {
  //       document.removeEventListener("mouseleave", handleMouseLeave);
  //       document.removeEventListener("mouseenter", handleMouseEnter);
  //       document.removeEventListener("keydown", handleKeyDown);
  //       document.removeEventListener("click", handleClick);
  //     };
  //   }, []);
  // };

  // useUnload(() => {
  //   if (window.preventUnload) {
  //     authCtx.logout();
  //   }
  // });

  // usePreventUnload();

  return(
    <>
    <Link to="/">
    <div className={"logo-container-fixed"}>
      <img src={logo} className="logo-img" alt="logo" />
      <img src={logoText} className="logo-text" alt="logoText" />
    </div>
  </Link>
<Layout>
  <Routes>
    <Route path="/*" element = {<LogginedPage/>} />
  </Routes>
</Layout>
</>
  )
  
}

export default App;
