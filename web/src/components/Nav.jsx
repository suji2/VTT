import React, { useEffect, useState } from 'react';
import { NavLink } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';
import './Nav.css';
import { postLogout } from '../api/postLogout';

const Nav = ({ isLogin, setIsLogin }) => {
<<<<<<< HEAD
  const handleLogout = async () => {
    await postLogout();
=======
  const [userInfo, setUserInfo] = useState(null);

  useEffect(() => {
    if (isLogin) {
      const fetchUserInfo = async () => {
        try {
          const accessToken = Cookies.get('ACCESS_TOKEN');
          const response = await axios.get('http://localhost:8080/api/user', {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
            withCredentials: true,
          });
          setUserInfo(response.data);
        } catch (error) {
          console.error('사용자 정보를 가져오는 중 오류 발생', error);
        }
      };

      fetchUserInfo();
    }
  }, [isLogin]);

  const handleLogout = () => {
    Cookies.remove('ACCESS_TOKEN');
>>>>>>> haneul
    setIsLogin(false);
    window.location.href = '/'; // 로그아웃 성공 시 리디렉트
  };

  return (
    <div className="NAV">
      <header className="header">
        <NavLink to="/" className="nav-title" end>VTT(Video To Text)</NavLink>
        <nav className="nav-links">
          <NavLink to="/" className={({ isActive }) => isActive ? 'nav-link active-link' : 'nav-link'} end>HOME</NavLink>
          <NavLink to="/about" className={({ isActive }) => isActive ? 'nav-link active-link' : 'nav-link'}>ABOUT</NavLink>
          {isLogin ? (
            <div className="nav-auth">
              <NavLink to="#" onClick={handleLogout} className="nav-link">Logout</NavLink>
              {userInfo && (
                <div className="profile-info">
                  <img src={userInfo.picture} alt="Profile" className="profile-picture" />
                </div>
              )}
            </div>
          ) : (
            <NavLink to="/login/oauth2/code/google" className="nav-link">Login</NavLink>
          )}
        </nav>
      </header>
    </div>
  );
};

export default Nav;
