import {GET, POST} from "../auth/fetch-action";

const createTokenHeader = (token) => {
    return {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    }
  }

  export const checkDevHandler = (token) => {
    const URL = '/dev/log';
    const response = GET(URL, createTokenHeader(token));
    return response;
    };

  export const changeLoggingLevelHandler = (loggingLevel, path, token) => {
    console.log("signupActionHandler")
    const URL = `/dev/actuator/loggers/${path}`
    const loggingLevelObject = {configuredLevel: loggingLevel};
    const response = POST(URL, loggingLevelObject, createTokenHeader(token));
    return response;
};