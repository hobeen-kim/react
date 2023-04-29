import { useState, useRef, useContext, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthContext from '../../store/auth/auth-context';
import {DevContext} from '../../store/dev/dev-context';
import LoadingSpinner from '../../UI/LoadingSpinner';


const DevLogging = () => {

  const devCtx = useContext(DevContext);
  const authCtx = useContext(AuthContext);
  const [logLevel, setLogLevel] = useState([]);
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    const fetchDev = async () => {
      setIsLoading(true);
      await devCtx.getLogLevel();
      setIsLoading(false);
    }
    fetchDev();
  }, []);

  useEffect(() => {
    setLogLevel(devCtx.logLevel);
  }, [devCtx.logLevel]);

  const LoggingLevelChangeHandler = () => {
      setIsLoading(true);
      devCtx.changeLoggingLevel();
      setIsLoading(false);
  }


    return (
      <div>
      {isLoading && <LoadingSpinner asOverlay />}
        <div>
          <h2>서비스 로깅 레벨</h2>
          <h2>{logLevel?.serviceLoggingLevel}</h2>
          <button onClick={LoggingLevelChangeHandler}>서비스 로깅 레벨 변경</button>
          <h2>시큐리티 로깅 레벨</h2>
          <h2>{logLevel?.securityLoggingLevel}</h2>
          <h2>SQL 로깅 레벨</h2>
          <h2>{logLevel?.hibernateLoggingLevel}</h2>
          
        </div>
      </div>
    )
      
  }

export default DevLogging;