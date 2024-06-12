import React from 'react';
import './LoginButton.css';

const LoginButton = () => {
  const handleLogin = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  };

  return (
    <div className="login-container">
      <img src="/VTT.webp" alt="VTT" className="login-image" />
      <button className="login-button" onClick={handleLogin}>
        로그인하러 가기
      </button>
    </div>
  );
}

export default LoginButton;
