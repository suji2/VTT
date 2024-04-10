import React, { useState, useRef } from 'react';
import { NavLink } from 'react-router-dom';
import './Home.css';

function Home({ data }) {
  const [videoSrc, setVideoSrc] = useState('');
  const [uploading, setUploading] = useState(false);
  const fileInputRef = useRef(null);

  const handleVideoUpload = (event) => {
    const file = event.target.files[0];
    if (file) {
      const src = URL.createObjectURL(file);
      setVideoSrc(src);
      setUploading(true);
    }
  };

  const handleCloseVideo = () => {
    setVideoSrc('');
    setUploading(false);
    fileInputRef.current.value = null;
  };

  return (
    <div className="home">
      <header className="header">
        <NavLink to="/" className="nav-title" end>VTT(Video To Text)</NavLink>
        <nav className="nav-links">
          <NavLink to="/" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"} end>HOME</NavLink>
          <NavLink to="/about" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>ABOUT</NavLink>
          <NavLink to="/version" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>Q&A</NavLink>
          <NavLink to="/login" className={({ isActive }) => isActive ? "nav-link active-link" : "nav-link"}>Login</NavLink>
        </nav>
        </header>
      <main className="main-content">
        <div className={`upload-box ${uploading ? 'uploading' : ''}`}>
          {!uploading && (
            <label htmlFor="video-upload" className="upload-label">영상업로드</label>
          )}
          <input
            type="file"
            accept="video/*"
            onChange={handleVideoUpload}
            ref={fileInputRef}
            style={{ display: 'none' }}
            id="video-upload"
          />
          {uploading && (
            <div className="video-container">
              <video controls src={videoSrc} className="video-player" />
              <button onClick={handleCloseVideo} className="close-button">X</button>
            </div>
          )}
        </div>
        {data && data.map((video) => (
          <div key={video.id} className="video-card">
            <h2>{video.title}</h2>
            <p>{video.description}</p>
            <p>{video.channel}</p>
            <p>{video.thumbnail}</p>
            <p>{video.date}</p>
            <a href={video.url} target="_blank" rel="noopener noreferrer">URL</a>
          </div>
        ))}
      </main>
      <div className="footer-links">
        <a href="https://github.com/suji2/VTT.git" target="_blank" rel="noopener noreferrer">
          <img src="/git.png" alt="GitHub" className="footer-icon" />
        </a>
      </div>
    </div>
  );
}

export default Home;
