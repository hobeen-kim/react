import React from 'react';
import ReactDOM from 'react-dom/client';
import App from './App';
import { AuthContextProvider } from './store/auth/auth-context';
import { BrowserRouter } from 'react-router-dom';
import Modal from 'react-modal';
import './public/css/app.css';
import { ValidationProvider } from './store/validation/validation';



const root = ReactDOM.createRoot(document.getElementById('root'));
Modal.setAppElement('#root'); // #root는 루트 엘리먼트의 id 입니다.

root.render(
  <BrowserRouter>
  <AuthContextProvider>
    <ValidationProvider>
      <App />
    </ValidationProvider>
  </AuthContextProvider>
</BrowserRouter>,
  document.getElementById('root')

);