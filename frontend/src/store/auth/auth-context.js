import React, { useState, useEffect, useCallback } from "react";
import * as authAction from './auth-action'; 
let logoutTimer;

const AuthContext = React.createContext({
  accessToken: '',
  userObj: {memberId: '', email: '', name: '', nickname: '', authority: ''},
  isLoggedIn: false,
  isDev: false,
  isSuccess: false,
  isGetSuccess: false,
  cssState:'',
  signup: (memberId, email, password, name, nickname) =>  {},
  login: (memberId, password) => {},
  logout: () => {},
  getUser: () => {},
  changeNickname: (memberId, nickname) => {},
  changePassword: (exPassword, newPassword) => {},
  cssStateChange: () => {},
});

export const AuthContextProvider = (props) => {

  const tokenData = authAction.retrieveStoredToken();

  const [accessToken, setAccessToken] = useState(tokenData ? tokenData.accessToken : '');
  const [cssState, setCssState] = useState(0);
  const [userObj, setUserObj] = useState({
    memberId:'',
    email: '',
    name:'',
    nickname: '',
    authority: '',
  });
  
  const [isSuccess, setIsSuccess] = useState(false);
  const [isGetSuccess, setIsGetSuccess ] = useState(false);
  const [isDev, setIsDev] = useState(false);

  const userIsLoggedIn = !!accessToken;

  const signupHandler = (memberId, email, password, name, nickname) => {
    setIsSuccess(false);
    const response = authAction.signupActionHandler(memberId, email, password, name, nickname);
    response.then((result) => {
      if (result !== null) {
        setIsSuccess(true);
        window.alert("회원가입이 완료되었습니다. 로그인해주세요.")
      }
    }).catch((err) => {
      window.alert('에러가 발생했습니다. 다시 시도해주세요.');
    });
  };

  const loginHandler = (memberId, password) => {
    setIsSuccess(false);
    
    const data = authAction.loginActionHandler(memberId, password);
    data.then((result) => {
      if (result !== null) {
        const loginData = result.data;
        setAccessToken(loginData.accessToken);
        logoutTimer = setTimeout(
          logoutHandler,
          authAction.loginTokenHandler(
            loginData.accessToken, loginData.accessTokenExpiresIn, loginData.refreshTokenExpiresIn)
        );
        setIsSuccess(true);
        setCssState(1);
      }
    })
  };

  const logoutHandler = useCallback(() => {
    setAccessToken('');
    setUserObj({memberId:'', email: '', name: '', nickname: '', authority: ''});
    authAction.logoutActionHandler();
    if (logoutTimer) {
      clearTimeout(logoutTimer);
    }
    setCssState(0);
  }, []);

  const getUserHandler = () => {
    setIsGetSuccess(false);
    const data = authAction.getUserActionHandler(accessToken);
    data.then((result) => {
      if (result !== null) {
        const userData = result.data;
        if(userData.authority === 'ROLE_DEVELOPER') {
          setIsDev(true);
        }
        setUserObj(userData);
        setIsGetSuccess(true);
      }
    })    
  };

  const changeNicknameHandler = (memberId, nickname) => {
    setIsSuccess(false);

    const data = authAction.changeNicknameActionHandler(memberId, nickname, accessToken);
    data.then((result) => {
      if (result !== null) {
        const userData = result.data;
        setUserObj(userData);
        setIsSuccess(true);
      }
    })
  };

  const changePaswordHandler = (exPassword, newPassword) => {
    setIsSuccess(false);
    const data = authAction.changePasswordActionHandler(exPassword, newPassword, accessToken);
    data.then((result) => {
      if (result !== null) {
        setIsSuccess(true);
        window.alert("비밀번호가 변경되었습니다. 다시 로그인해주세요.");
        logoutHandler();
      }
    });
  };

  const cssStateChangeHandler = () => {
    setCssState(2);
  }

  // 시간에 따른 자동 로그아웃
  useEffect(() => {
    if(tokenData) {
      logoutTimer = setTimeout(logoutHandler, tokenData.duration);
    }
  }, [tokenData, logoutHandler]);


  const contextValue ={
    accessToken,
    userObj,
    isLoggedIn: userIsLoggedIn,
    isDev,
    isSuccess,
    isGetSuccess,
    cssState,
    signup: signupHandler,
    login: loginHandler,
    logout: logoutHandler,
    getUser: getUserHandler,
    changeNickname: changeNicknameHandler,
    changePassword: changePaswordHandler,
    cssStateChange : cssStateChangeHandler
  }
  
  return(
    <AuthContext.Provider value={contextValue}>
      {props.children}
    </AuthContext.Provider>
  )
}

export default AuthContext;