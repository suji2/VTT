import React, { useState, useEffect } from 'react';
import { NavLink, useNavigate } from 'react-router-dom';
import './Version.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

function Version() {
  const [searchTerm, setSearchTerm] = useState("");
  const [questions, setQuestions] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchQuestions();
  }, []);

  const fetchQuestions = async () => {
    try {
      const response = await fetch('http://192.168.34.10:8080/api/questions'); // 백엔드 URL로 변경
      const data = await response.json();
      setQuestions(data);
    } catch (error) {
      console.error('Error fetching questions:', error);
    }
  };

  // 검색 핸들러 함수
  const handleSearch = (e) => {
    e.preventDefault();
    console.log("검색:", searchTerm);
    // 검색 로직 추가
  };

  // 글쓰기 버튼 클릭 핸들러
  const handleWrite = () => {
    navigate('/write');
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
        {questions.map((question, index) => (
          <div key={question.question_num} className="table-row">
            <div className="table-cell">{index + 1}</div>
            <div className="table-cell">{question.title}</div>
            <div className="table-cell">{new Date(question.createDate).toLocaleDateString()}</div>
            <div className="table-cell">{question.answerYN}</div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Version;
