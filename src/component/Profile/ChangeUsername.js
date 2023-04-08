import React, { useContext, useRef } from 'react';
import { useNavigate } from 'react-router-dom';
import AuthContext from '../../store/auth/auth-context';

const ChangeUsername = () => {

  let navigate = useNavigate();

  const authCtx = useContext(AuthContext);
  const nicknameInputRef = useRef(null);

  
  const submitHandler = (event) => {
    event.preventDefault();
    const MemberId = authCtx.userObj?.memberId || 'memberId';
    const enteredNickname = nicknameInputRef.current.value;
    console.log(MemberId);
    console.log('change nickname start');
    authCtx.changeNickname(MemberId, enteredNickname);
    if (authCtx.isSuccess) {
      alert("변경 되었습니다.");
      authCtx.getUser();
      navigate("/", { replace: true });
    }
  }

  return (
    <form onSubmit={submitHandler}>
      <div>
        <label htmlFor='username'>New Nickname</label>
        <input type='text' id='username'minLength={3} required ref={nicknameInputRef}/>
      </div>
      <div>
        <button type='submit'>Change Username</button>
      </div>
    </form>
  );
}

export { ChangeUsername };