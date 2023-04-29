import React, { createContext, useState } from 'react';
import * as authAction from '../auth/auth-action'; 
import {changeLoggingLevelHandler, checkDevHandler} from './dev-action';

export const DevContext = createContext({
    error: null,
    logLevel: [],
    changeLoggingLevel : async (loggingLevel, path) => {},
    getLogLevel : async () => {}
});

export const DevContextProvider = ({ children }) => {

    const tokenData = authAction.retrieveStoredToken();
    let initialToken;
    if (tokenData) {
      initialToken = tokenData.accessToken;
    }

    const [error, setError] = useState(null);
    const [logLevel, setLogLevel] = useState([]);
    const [token, setToken] = useState(initialToken);

    const getLogLevel = async () => {
        setError(null);
        try {
            const response = await checkDevHandler(token);
            setLogLevel(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }
        
    const changeLoggingLevel = async (loggingLevel, path) => {
        setError(null);
        try {
            if(path === 'serviceLoggingLevel'){
                path = 'soccer.backend';
            }else if(path === 'securityLoggingLevel'){
                path = 'org.springframework.security';
            }else if(path === 'hibernateLoggingLevel'){
                path = 'org.hibernate.SQL';
            }

            const response = await changeLoggingLevelHandler(loggingLevel, path, token);
            setLogLevel(response.data);
        } catch (error) {
            setError(error.message || 'Something went wrong!');
        }
    }


    const contextValue = {
        error,
        logLevel,
        changeLoggingLevel,
        getLogLevel
    }

    return (
        <DevContext.Provider value={contextValue}>
            {children}
        </DevContext.Provider>
    );
}

