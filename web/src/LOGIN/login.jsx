import React, { useState } from 'react';
import { NavLink } from 'react-router-dom';
import axios from 'axios';

function Login() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [loginMessage, setLoginMessage] = useState('');

  const handleGoogleLogin = () => {
    const clientId = '935624103884-79phbvahdot9tec5lfpfclia0uvekf8h.apps.googleusercontent.com'; // 클라이언트 ID 입력
    const redirectUri = encodeURIComponent('http://localhost:3000');
    const scope = encodeURIComponent('https://www.googleapis.com/auth/youtube');
    const authUrl = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=${scope}`;

    // 구글 로그인 페이지로 리디렉션합니다.
    window.location.href = authUrl;

    // 로그인이 성공했을 때 호출되는 함수
    const handleLoginSuccess = () => {
      setLoginMessage('로그인 성공!');
      setIsLoggedIn(true); // 로그인 상태를 변경합니다.
    };

    // 구글 로그인이 성공했을 때 호출되어야 할 함수
    // 여기서는 호출하지 않고, 로그인 성공 후에 호출되도록 작성합니다.
  };

  const handleLogout = () => {
    // 로그아웃 로직을 여기에 추가하세요
    setIsLoggedIn(false);
  };

  const getUserInfo = async () => {
    try {
      const response = await axios.get("http://localhost:8080/user");
      console.log(response.data);
      // 사용자 정보 처리
    } catch (error) {
      console.error('사용자 정보를 가져오는 데 오류가 발생했습니다:', error);
      // 오류 처리
    }
  };

  const handleLoginSuccess = () => {
    setLoginMessage('로그인 성공!');
    setIsLoggedIn(true); // 로그인 상태를 변경합니다.
  };

  return (
    <div className="Login">
      <header className="header">
        <NavLink to="/" className="nav-title" end> VTT(Video To Text)</NavLink>
        <nav className="nav-links">
          <NavLink to="/" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"} end>HOME</NavLink>
          <NavLink to="/about" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>ABOUT</NavLink>
          <NavLink to="/version" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>Q&A</NavLink>
          <NavLink to={isLoggedIn ? "/logout" : "/login"} className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"} onClick={isLoggedIn ? handleLogout : handleGoogleLogin}>
            {isLoggedIn ? "logout" : "login"}
          </NavLink>
        </nav>
      </header>
      {/* 로그인 성공 메시지 출력 */}
      {loginMessage && <p>{loginMessage}</p>}
      {/* 사용자 정보 가져오기 버튼 */}
      {isLoggedIn && <button onClick={getUserInfo}>사용자 정보 가져오기</button>}
      {/* 구글 로그인 버튼 */}
      <div className="google-login-container">
        <button onClick={handleGoogleLogin} className="google-login-button">
          구글 로그인
        </button>
      </div>
      <div className="footer-links">
        <a href="https://github.com/suji2/VTT.git" target="_blank" rel="noopener noreferrer">
          <img src="/git.png" alt="GitHub" className="footer-icon" />
        </a>
      </div>
    </div>
  );
}

export default Login;
