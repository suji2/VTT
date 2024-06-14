import React, { useEffect, useState } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';
import './Nav.css';
import { postLogout } from '../api/postLogout';

const Nav = ({ isLogin, setIsLogin }) => {
  const [userInfo, setUserInfo] = useState(null);
  const navigate = useNavigate();

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

  const handleLogout = async () => {
    try {
      await postLogout();
      Cookies.remove('ACCESS_TOKEN');
      setIsLogin(false);
      navigate('/'); // 로그아웃 성공 시 로그인 페이지로 리디렉트
    } catch (error) {
      console.error('로그아웃 중 오류 발생', error);
    }
  };

  return (
    <div className="NAV">
      <header className="header">
        <NavLink to="/home" className="nav-title" end>VTT(Video To Text)</NavLink>
        <nav className="nav-links">
          <NavLink to="/home" className={({ isActive }) => isActive ? 'nav-link active-link' : 'nav-link'} end>HOME</NavLink>
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
