import React from 'react';
import './LoginButton.css';

const LoginButton = () => {
  const handleLoginClick = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google'; // 백엔드 OAuth2 로그인 엔드포인트로 리디렉션
  };

  return (
    <div className="login-container">
      <img src="/max.PNG" alt="VTT" className="login-image" />
      <button onClick={handleLoginClick} className="login-button">
        로그인하러 가기
      </button>
    </div>
  );
};

export default LoginButton;
