import React from 'react';
import { NavLink } from 'react-router-dom';
import './Nav.css';
import { postLogout } from '../api/postLogout';

const Nav = ({ isLogin, setIsLogin }) => {
  const handleLogout = async () => {
    await postLogout();
    setIsLogin(false);
    window.location.href = '/'; // 로그아웃 성공 시 리디렉트
  };

  return (
    <div className="NAV">
      <header className="header">
        <NavLink to="/" className="nav-title" end>VTT(Video To Text)</NavLink>
        <nav className="nav-links">
          <NavLink to="/" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"} end>HOME</NavLink>
          <NavLink to="/about" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>ABOUT</NavLink>
          <NavLink to="/version" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>Q&A</NavLink>
          {isLogin ? (
            <NavLink to="#" onClick={handleLogout} className="nav-link">Logout</NavLink>
          ) : (
            <NavLink to="/login/oauth2/code/google" className="nav-link">Login</NavLink>
          )}
        </nav>
      </header>
    </div>
  );
}

export default Nav;