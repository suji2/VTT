import React from 'react';
import { NavLink } from 'react-router-dom';
import './Nav.css';

const Nav = ({ isLogin, setIsLogin }) => {
  const handleLogout = () => {
    localStorage.removeItem('accessToken');
    setIsLogin(false);
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
      <div className="footer-links">
        <a href="https://github.com/suji2/VTT.git" target="_blank" rel="noopener noreferrer">
          <img src="/git.png" alt="GitHub" className="footer-icon" />
        </a>
      </div>
    </div>
  );
}

export default Nav;