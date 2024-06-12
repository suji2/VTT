import React, { useState, useEffect } from 'react';
import { useNavigate, useParams, NavLink } from 'react-router-dom';
import './EditForm.css';

function EditForm() {
  const { questionId } = useParams();
  const [title, setTitle] = useState('');
  const [content, setContent] = useState('');
  const navigate = useNavigate();

  useEffect(() => {
    const fetchQuestionDetails = async () => {
      try {
        const response = await fetch(`/api/question/${questionId}`);
        const data = await response.json();
        setTitle(data.title);
        setContent(data.content);
      } catch (error) {
        console.error('Error fetching question details:', error);
      }
    };

    fetchQuestionDetails();
  }, [questionId]);

  const handleUpdate = async (e) => {
    e.preventDefault();

    try {
      const response = await fetch(`/api/question/${questionId}`, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ title, content }),
      });

      if (response.ok) {
        window.alert('수정 완료');
        navigate('/version');
      } else {
        window.alert('수정 실패');
      }
    } catch (error) {
      console.error('Error updating question:', error);
      window.alert('수정 중 오류가 발생했습니다');
    }
  };

  const handleDelete = async () => {
    const isConfirmed = window.confirm('정말 삭제하시겠습니까?');

    if (isConfirmed) {
      try {
        const response = await fetch(`/api/question/${questionId}`, {
          method: 'DELETE',
        });

        if (response.ok) {
          window.alert('삭제 완료');
          navigate('/version');
        } else {
          window.alert('삭제 실패');
        }
      } catch (error) {
        console.error('Error deleting question:', error);
        window.alert('삭제 중 오류가 발생했습니다');
      }
    }
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
      <div className="EditForm">
        <form onSubmit={handleUpdate}>
          <div>
            <label htmlFor="title">제목</label>
            <input
              type="text"
              id="title"
              value={title}
              onChange={(e) => setTitle(e.target.value)}
            />
          </div>
          <div>
            <label htmlFor="content">내용</label>
            <textarea
              id="content"
              rows="10"
              value={content}
              onChange={(e) => setContent(e.target.value)}
            ></textarea>
          </div>
          <div>
            <button type="submit">수정</button>
            <button type="button" onClick={handleDelete}>삭제</button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default EditForm;
