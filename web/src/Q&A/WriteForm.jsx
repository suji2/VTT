import React, { useState, useRef } from 'react';
import { useNavigate, NavLink } from 'react-router-dom';
import './WriteForm.css';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faCamera } from '@fortawesome/free-solid-svg-icons';

function WriteForm() {
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const [file, setFile] = useState(null);
  const navigate = useNavigate();
  const fileInputRef = useRef(null);

  const handleSubmit = async (e) => {
    e.preventDefault();

    // 저장 확인
    const isConfirmed = window.confirm("저장하시겠습니까?");

    if (isConfirmed) {
      try {
        // 데이터 전송을 위한 FormData 생성
        const formData = new FormData();
        formData.append('title', title);
        formData.append('content', content);
        formData.append('secretYN', 'N'); // 비공개 여부, 여기서는 기본값을 'N'으로 설정
        if (file) {
          formData.append('file', file);
        }

        // FormData 내용 확인
        for (let pair of formData.entries()) {
          console.log(pair[0]+ ', ' + pair[1]); 
        }

        // 백엔드에 데이터 전송
        const response = await fetch(' http://10.20.104.173:3000/question', {
          method: 'POST',
          body: formData,
        });

        if (response.ok) {
          // 저장 완료 시 이전 페이지로 이동
          window.alert("저장 완료");
          navigate('/version'); // Q&A 페이지로 이동
        } else {
          // 저장 실패 시 에러 메시지 표시
          const errorData = await response.json();
          console.error("Error response:", errorData);
          window.alert("저장 실패");
        }
      } catch (error) {
        console.error("Error saving data:", error);
        window.alert("저장 중 오류가 발생했습니다");
      }
    } else {
      // 취소 시 알림
      window.alert("취소됐습니다");
      navigate('/version'); // Q&A 페이지로 이동
    }
  };

  const handleCameraButtonClick = () => {
    fileInputRef.current.click();
  };

  const handleFileChange = (e) => {
    const selectedFile = e.target.files[0];
    if (selectedFile) {
      setFile(selectedFile);
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
