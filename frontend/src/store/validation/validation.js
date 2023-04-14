import React, { createContext } from 'react';

export const Validation = createContext({
    error: '',
    memberIdValidator: (memberId) => {},
    emailValidator: (email) => {},
    passwordValidator: (password) => {},
    nameValidator: (name) => {},
    nicknameValidator: (nickname) => {},
    rangeValidator: (value, min, max) => {},
    
});

export const ValidationProvider = ({ children }) => {

    const memberIdValidator = (memberId) => {
        const memberIdRegex =  /^(?=.*[a-z])\w{4,20}$/;
        if(!memberIdRegex.test(memberId)) return '아이디는 4~20자의 영문 소문자와 숫자로만 입력해주세요.';
        else return '';
    };

    const emailValidator = (email) => {
        const emailRegex = /([\w-.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
        if (!emailRegex.test(email)) return '올바른 이메일 형식이 아닙니다.';
        else return '';
    };

    const passwordValidator = (password) => {
        const passwordRegex = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{8,30}$/;
        if (!passwordRegex.test(password)) return '숫자+영문자+특수문자 조합으로 8자리 이상 입력해주세요!';
        else return '';
    };

    const nameValidator = (name, min, max) => {
        const nameRegex = new RegExp(`^[가-힣a-zA-Z]{${min},${max}}$`);
        if (!nameRegex.test(name))  return `글자 수 ${min}~${max} 사이의 올바른 이름을 입력해주세요.`;
        else return '';
        };

    const nicknameValidator = (nickname) => {
        const nickNameRegex = /^([a-zA-Z가-힣]+\d*){1,20}$/;
        if (!nickNameRegex.test(nickname) || nickname.length < 1) return '올바른 닉네임을 입력해주세요.';
        else return '';
    }
        
    const rangeValidator = (value, min, max) => {
        if (value < min || value > max) return `숫자 범위 ${min}~${max} 사이의 값을 입력해주세요.`;
        else return '';
    };


    const contextValue = {
        memberIdValidator,
        emailValidator,
        passwordValidator,
        nameValidator,
        nicknameValidator,
        rangeValidator,

    }



    return (
        <Validation.Provider value={contextValue}>
            {children}
        </Validation.Provider>
    );
}
