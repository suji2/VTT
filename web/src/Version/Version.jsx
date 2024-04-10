import React from 'react';
import { NavLink, useNavigate } from 'react-router-dom'; // useNavigate가 정확히 import되었는지 확인하세요.
import './Version.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

function Version() {
  const [searchTerm, setSearchTerm] = React.useState("");
  const navigate = useNavigate(); // 여기에서 useNavigate를 호출하여 navigate 함수를 생성합니다.

  // 검색 핸들러 함수
  const handleSearch = (e) => {
    e.preventDefault();
    console.log("검색:", searchTerm);
  };

  // Version 컴포넌트 내의 handleWrite 함수
  const handleWrite = () => {
    navigate('/write'); // '/version/write' 대신 '/write'를 사용합니다.
  };
  
  return (
    <div className="Version">
      <header className="header">
        <NavLink to="/" className="nav-title" end> VTT(Video To Text)</NavLink>
        <nav className="nav-links">
          <NavLink to="/" className={({ isActive }) => (isActive ? 'nav-link active-link' : 'nav-link')} end>HOME</NavLink>
          <NavLink to="/about" className={({ isActive }) => (isActive ? 'nav-link active-link' : 'nav-link')}>ABOUT</NavLink>
          <NavLink to="/version" className={({ isActive }) => (isActive ? 'nav-link active-link' : 'nav-link')}>Q&A</NavLink>
          <NavLink to="/login" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>Login</NavLink>
        </nav>
      </header>
      <div className="search-container">
        <form onSubmit={handleSearch} className="search-form">
          <input
            type="text"
            className="search-input"
            placeholder="검색어를 입력하세요"
            value={searchTerm}
            onChange={(e) => setSearchTerm(e.target.value)}
          />
          <button type="submit" className="search-button">
            <FontAwesomeIcon icon={faSearch} />
          </button>
          <button type="button" className="write-button" onClick={handleWrite}>글쓰기</button>
        </form>
      </div>
      <div className="content-table">
        <div className="table-row header">
          <div className="table-cell">번호</div>
          <div className="table-cell">제목</div>
          <div className="table-cell">작성일</div>
          <div className="table-cell">진행상황</div>
        </div>
      </div>
    </div>
  );
}

export default Version;