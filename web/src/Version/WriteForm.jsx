import React, { useState, useRef } from 'react';
import { useNavigate, NavLink } from 'react-router-dom';
import './WriteForm.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCamera } from '@fortawesome/free-solid-svg-icons';

function WriteForm() {
  // 각 필드별로 상태 설정
  const [password, setPassword] = useState('');
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();
  const fileInputRef = useRef(null);

  const handleSubmit = (e) => {
    e.preventDefault();

    // 저장 확인
    const isConfirmed = window.confirm("저장하시겠습니까?");

    if (isConfirmed) {
      // 확인 시 로직 처리
      console.log("Submitted: ", { password, title, content });
      window.alert("저장 완료");
      navigate(-1); // 이전 페이지로 이동
    } else {
      // 취소 시 알림
      window.alert("취소됐습니다");
      navigate(-1); // 취소 시에도 이전 페이지로 이동
    }
  };

  const handleCameraButtonClick = () => {
    fileInputRef.current.click();
  };

  const handleFileChange = (e) => {
    const file = e.target.files[0];
    if (file) {
      console.log("Selected file:", file);
      // You can add further processing of the selected file here
    }
  };

  return (
    <div className="WriteForm">
      <header className="header">
        <NavLink to="/" className="nav-title" end> VTT(Video To Text)</NavLink>
        <nav className="nav-links">
          <NavLink to="/" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"} end>HOME</NavLink>
          <NavLink to="/about" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>ABOUT</NavLink>
          <NavLink to="/version" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>Q&A</NavLink>
          <NavLink to="/login" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>Login</NavLink>
        </nav>
      </header>
      <div className="form-container">
        <form onSubmit={handleSubmit}>
          <div>
            <label htmlFor="title">제목</label>
            <input
              type="text"
              id="title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />
          </div>
          <div className="content-container">
            <label htmlFor="content">내용</label>
            <textarea
              id="content"
              rows="10"
              value={content}
              onChange={(e) => setContent(e.target.value)}
              style={{ resize: 'none' }} // 내용 입력 칸 크기 변경 불가하게 설정
            ></textarea>
            <button type="button" className="camera-button" onClick={handleCameraButtonClick}>
              <FontAwesomeIcon icon={faCamera} />
            </button>
            <input
              type="file"
              accept="image/*" // 이미지 파일만 선택 가능
              ref={fileInputRef}
              style={{ display: 'none' }}
              onChange={handleFileChange}
            />
          </div>
          <div>
            <button type="submit">저장</button>
          </div>
        </form>
      </div>
      <div className="footer-links">
        <a href="https://github.com/suji2/VTT.git" target="_blank" rel="noopener noreferrer">
          <img src="/git.png" alt="GitHub" className="footer-icon" />
        </a>
      </div>
    </div>
  );
}

export default WriteForm;
