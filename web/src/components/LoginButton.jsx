import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import Cookies from 'js-cookie';
import './LoginButton.css';

const LoginButton = ({ setIsLogin }) => {
  const navigate = useNavigate();

  useEffect(() => {
    const accessToken = Cookies.get('ACCESS_TOKEN');
    if (accessToken) {
      setIsLogin(true); // 로그인 상태 업데이트
      navigate('/home'); // Access token이 있는 경우 home으로 리디렉션
    }
  }, [navigate, setIsLogin]);

  const handleLoginClick = () => {
    window.location.href = 'http://localhost:8080/oauth2/authorization/google'; // 백엔드 OAuth2 로그인 엔드포인트로 리디렉션
  };

  return (
    <div className="login-container">
      <img src="/hhhhh.gif" alt="VTT" className="login-image" />
      <button onClick={handleLoginClick} className="login-button">
        로그인하러 가기
      </button>
    </div>
  );
};

export default LoginButton;
