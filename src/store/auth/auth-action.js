import {GET, POST} from "./fetch-action";

const createTokenHeader = (token) => {
    return {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    }
  }

const calculateRemainingTime = (expirationTime) => {
const currentTime = new Date().getTime();
const adjExpirationTime = new Date(expirationTime).getTime();
const remainingDuration = adjExpirationTime - currentTime;
return remainingDuration;
};

export const loginTokenHandler = (token, expirationTime) => {
    localStorage.setItem('token', token);
    localStorage.setItem('expirationTime', String(expirationTime));
  
    const remainingTime = calculateRemainingTime(expirationTime);
    return remainingTime;
}

export const retrieveStoredToken = () => {
    const storedToken = localStorage.getItem('token');
    const storedExpirationDate = localStorage.getItem('expirationTime') || '0';
  
    const remainigTime = calculateRemainingTime(+ storedExpirationDate);
  
    if(remainigTime <= 1000) {
      localStorage.removeItem('token');
      localStorage.removeItem('expirationTime');
      return null
    }

    return {
        token: storedToken,
        duration: remainigTime
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
    const response = POST(URL, loginObject, {});
  
    return response;
};

export const logoutActionHandler = () => {
    localStorage.removeItem('token');
    localStorage.removeItem('expirationTime');
  };
  
  export const getUserActionHandler = (token) => {
    const URL = '/member/me';
    const response = GET(URL, createTokenHeader(token));
    return response;
  }
  
  export const changeNicknameActionHandler = ( memberId, nickname, token) => {
    const URL = '/member/nickname';
    const changeNicknameObj = {memberId, nickname };
    const response = POST(URL, changeNicknameObj, createTokenHeader(token));
  
    return response;
  }
  
  export const changePasswordActionHandler = (exPassword, newPassword,token) => {
    const URL = '/member/password';
    const changePasswordObj = { exPassword, newPassword }
    const response = POST(URL, changePasswordObj, createTokenHeader(token));
    return response;
  }