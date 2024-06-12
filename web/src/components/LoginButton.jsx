import React from 'react';
import './LoginButton.css'; // CSS 파일 임포트

function LoginButton() {
  const handleLoginClick = () => {
    window.location.href = "https://192.168.34.29:3000/oauth2/authorization/google"; // 구글 로그인 엔드포인트로 리디렉션
  };

  return (
    <div className="login-container">
      <img src="/VTT.webp" alt="VTT" className="login-image" />
      <button onClick={handleLoginClick} className="login-button">
        로그인하러 가기
      </button>
    </div>
  );
}

export default LoginButton;
