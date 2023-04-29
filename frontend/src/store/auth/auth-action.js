import {GET, POST} from "./fetch-action";

const createTokenHeader = (accessToken) => {
    return {
      withCredentials: true,
      headers: {
        'Authorization': 'Bearer ' + accessToken
      }
    }
  }

const calculateRemainingTime = (expirationTime) => {
const currentTime = new Date().getTime();
const adjExpirationTime = new Date(expirationTime).getTime();
const remainingDuration = adjExpirationTime - currentTime;
return remainingDuration;
};

export const loginTokenHandler = (accessToken, accessTokenExpirationTime, refreshTokenExpirationTime) => {
    localStorage.setItem('accessToken', accessToken);
    localStorage.setItem('accessTokenExpirationTime', String(accessTokenExpirationTime));
  
    const remainingTime = calculateRemainingTime(refreshTokenExpirationTime);
    return remainingTime;
}

export const retrieveStoredToken = () => {
    const storedAccessToken = localStorage.getItem('accessToken');
    const storedRefreshExpirationDate = localStorage.getItem('refreshTokenExpirationTime') || '0';
  
    const remainingTime = calculateRemainingTime(+ storedRefreshExpirationDate);
  
    if(remainingTime <= 1000) {
      return null
    }

    return {
        accessToken: storedAccessToken,
        duration: remainingTime
    }
}

export const signupActionHandler = (memberId, email, password, name, nickname) => {
    const URL = '/auth/signup'
    const signupObject = {memberId, email, password, name, nickname};
    
    const response = POST(URL, signupObject, {});
    return response;
};

export const loginActionHandler = (memberId, password) => {
    const URL = '/auth/login';
    const loginObject = { memberId, password };
    const loginHeader = {
      withCredentials: true
    }
    const response = POST(URL, loginObject, loginHeader);
  
    return response;
};

export const logoutActionHandler = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('expirationTime');
  };
  
  
  export const getUserActionHandler = (accessToken) => {
    const URL = '/member/me';
    const response = GET(URL, createTokenHeader(accessToken));
    return response;
  }
  
  export const changeNicknameActionHandler = ( memberId, nickname, accessToken) => {
    
    const URL = '/member/nickname';
    const changeNicknameObj = {memberId, nickname };
    const response = POST(URL, changeNicknameObj, createTokenHeader(accessToken));
  
    return response;
  }
  
  export const changePasswordActionHandler = (exPassword, newPassword,accessToken) => {
    const URL = '/member/password';
    const changePasswordObj = { exPassword, newPassword }
    const response = POST(URL, changePasswordObj, createTokenHeader(accessToken));
    return response;
  }