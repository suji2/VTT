import React, { useState, useEffect } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './login.css';

function Login() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userInfo, setUserInfo] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    checkLoginStatus();
  }, []);

  const checkLoginStatus = async () => {
    try {
      const response = await axios.get("https://192.168.34.29:3000/user", { withCredentials: true });
      if (response.data) {
        setIsLoggedIn(true);
        setUserInfo(response.data);
        localStorage.setItem('accessToken', response.data.token); // 액세스 토큰 저장
      } else {
        setIsLoggedIn(false);
        setUserInfo(null);
      }
    } catch (error) {
      console.error('로그인 상태를 확인하는 데 오류가 발생했습니다:', error);
    }
  };

  const handleGoogleLogin = () => {
    window.location.href = "https://192.168.34.29:3000/oauth2/authorization/google"; // 백엔드 OAuth2 로그인 엔드포인트로 리디렉션
  };

  const handleLogout = async () => {
    try {
      await axios.get("https://192.168.34.29:3000/logout", { withCredentials: true });
      setIsLoggedIn(false);
      setUserInfo(null);
      localStorage.removeItem('accessToken'); // 로그아웃 시 액세스 토큰 제거
      navigate('/login'); // 로그아웃 후 로그인 페이지로 이동
    } catch (error) {
      console.error('로그아웃 중 오류가 발생했습니다:', error);
    }
  };

  return (
    <div className="Login">
      <header className="header">
        <NavLink to="/" className="nav-title" end> VTT(Video To Text)</NavLink>
        <nav className="nav-links">
          <NavLink to="/" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"} end>HOME</NavLink>
          <NavLink to="/about" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>ABOUT</NavLink>
          <NavLink to="/version" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>Q&A</NavLink>
          <NavLink to="#" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"} onClick={isLoggedIn ? handleLogout : handleGoogleLogin}>
            {isLoggedIn ? "logout" : "login"}
          </NavLink>
        </nav>
      </header>
      {isLoggedIn && userInfo ? (
        <div>
          <h1>User Profile</h1>
          <p>Name: {userInfo.name}</p>
          <p>Email: {userInfo.email}</p>
          {userInfo.picture && <img src={userInfo.picture} alt="Profile" />}
          <button onClick={() => navigate('/profile', { state: { user: userInfo } })}>
            유저 프로필 보기
          </button>
        </div>
      ) : (
        <div className="google-login-container">
          <button onClick={handleGoogleLogin} className="google-login-button">
            구글 로그인
          </button>
        </div>
      )}
      <div className="footer-links">
        <a href="https://github.com/suji2/VTT.git" target="_blank" rel="noopener noreferrer">
          <img src="/git.png" alt="GitHub" className="footer-icon" />
        </a>
      </div>
    </div>
  );
}

export default Login;
