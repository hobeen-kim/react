import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { AuthContextProvider } from './store/auth/auth-context';
import { BrowserRouter } from 'react-router-dom';
import Modal from 'react-modal';
import logo from "./public/image/logo.png";
import logoText from "./public/image/logo-text.png";
import './public/css/app.css';
import AuthContext from './store/auth/auth-context';


const root = ReactDOM.createRoot(document.getElementById('root'));
Modal.setAppElement('#root'); // #root는 루트 엘리먼트의 id 입니다.

root.render(
  <AuthContextProvider>
    <BrowserRouter>
        <App />
    </BrowserRouter>
  </AuthContextProvider>,
  document.getElementById('root')

);