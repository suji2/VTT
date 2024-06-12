import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
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
      const response = await fetch('/api/question'); // 프록시 설정을 통해 상대 경로로 변경
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
  
  // 게시글 클릭 핸들러
  const handleEdit = (questionId) => {
    navigate(`/edit/${questionId}`);
  };
  
  return (
    <div className="Version">
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
            <div className="table-cell">
              <span onClick={() => handleEdit(question.question_num)} className="question-title">
                {question.title}
              </span>
            </div>
            <div className="table-cell">{new Date(question.createDate).toLocaleDateString()}</div>
            <div className="table-cell">{question.answerYN}</div>
          </div>
        ))}
      </div>
    </div>
  );
}

export default Version;
