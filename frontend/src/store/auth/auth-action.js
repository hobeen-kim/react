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
    localStorage.setItem('accessTokenExpirationTime', String(refreshTokenExpirationTime));
  
    const remainingTime = calculateRemainingTime(refreshTokenExpirationTime);

    return remainingTime;
}

export const retrieveStoredToken = () => {
    const storedAccessToken = localStorage.getItem('accessToken');
    const storedAccessExpirationDate = localStorage.getItem('accessTokenExpirationTime') || '0';
  
    const remainingTime = calculateRemainingTime(+ storedAccessExpirationDate);
  
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
  localStorage.removeItem('accessToken');
  localStorage.removeItem('accessTokenExpirationTime');
};

  
  
  export const getUserActionHandler = (accessToken) => {
    const URL = '/members';
    const response = GET(URL, createTokenHeader(accessToken));
    return response;
  }
  
  export const changeNicknameActionHandler = ( memberId, nickname, accessToken) => {
    
    const URL = '/members/nickname';
    const changeNicknameObj = {memberId, nickname };
    const response = POST(URL, changeNicknameObj, createTokenHeader(accessToken));
  
    return response;
  }
  
  export const changePasswordActionHandler = (exPassword, newPassword,accessToken) => {
    const URL = '/members/password';
    const changePasswordObj = { exPassword, newPassword }
    const response = POST(URL, changePasswordObj, createTokenHeader(accessToken));
    return response;
  }