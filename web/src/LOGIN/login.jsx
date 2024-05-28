import React, { useState, useEffect } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import axios from 'axios';
import './login.css';  // Ensure the case matches the actual filename

function Login() {
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const [userInfo, setUserInfo] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    checkLoginStatus();
  }, []);

  const checkLoginStatus = async () => {
    try {
      const response = await axios.get("http://localhost:8080/user");
      if (response.data) {
        setIsLoggedIn(true);
        setUserInfo(response.data);
      } else {
        setIsLoggedIn(false);
        setUserInfo(null);
      }
    } catch (error) {
      console.error('로그인 상태를 확인하는 데 오류가 발생했습니다:', error);
    }
  };

  const handleGoogleLogin = () => {
    const clientId = '935624103884-79phbvahdot9tec5lfpfclia0uvekf8h.apps.googleusercontent.com'; // 클라이언트 ID 입력
    const redirectUri = encodeURIComponent('http://localhost:3000');
    const scope = encodeURIComponent('https://www.googleapis.com/auth/youtube');
    const authUrl = `https://accounts.google.com/o/oauth2/v2/auth?client_id=${clientId}&redirect_uri=${redirectUri}&response_type=code&scope=${scope}`;

    window.location.href = authUrl;
  };
  const handleLogout = async () => {
    try {
      await axios.get("http://localhost:8080/logout");
      setIsLoggedIn(false);
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
          <NavLink to={isLoggedIn ? "/logout" : "/login"} className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"} onClick={isLoggedIn ? handleLogout : handleGoogleLogin}>
            {isLoggedIn ? "logout" : "login"}
          </NavLink>
        </nav>
      </header>
      {isLoggedIn && userInfo && (
        <div>
          <h1>User Profile</h1>
          <p>Name: {userInfo.name}</p>
          <p>Email: {userInfo.email}</p>
        </div>
      )}
      {!isLoggedIn && (
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
