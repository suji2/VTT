import React, { useState, useEffect } from 'react';
import { NavLink } from 'react-router-dom';
import axios from 'axios';
import './Home.css';

function Home() {
  const [subscriptions, setSubscriptions] = useState([]);
  const [selectedChannel, setSelectedChannel] = useState(null);
  const [videos, setVideos] = useState([]);
  const [selectedVideo, setSelectedVideo] = useState(null);
  const [comments, setComments] = useState([]);

  useEffect(() => {
    // 구독 채널 가져오기
    const fetchSubscriptions = async () => {
      const token = localStorage.getItem('accessToken');
      if (token) {
        try {
          const response = await axios.get('/youtube/channel', {
            params: { token: token }
          });
          setSubscriptions(response.data.items);
        } catch (error) {
          console.error('Error fetching subscriptions:', error);
        }
      }
    };

    fetchSubscriptions();
  }, []);

  const handleChannelSelect = (channelId) => {
    setSelectedChannel(channelId);
    // 채널의 비디오 목록 가져오기
    const token = localStorage.getItem('accessToken');
    axios.get('/youtube/channelvideos', {
      params: { channelId: channelId, token: token, nextPageToken: '' }
    })
      .then(response => {
        setVideos(response.data.items);
      })
      .catch(error => console.error('Error fetching channel videos:', error));
  };

  const handleVideoSelect = (videoId) => {
    setSelectedVideo(videoId);
    // 비디오 상세 정보 및 댓글 가져오기
    const token = localStorage.getItem('accessToken');
    axios.get('/youtube/detail', { params: { videoId: videoId, token: token } })
      .then(response => {
        // 비디오 상세 정보 저장 (원하는 경우)
      })
      .catch(error => console.error('Error fetching video details:', error));

    axios.get('/youtube/comments', { params: { videoId: videoId, token: token } })
      .then(response => {
        setComments(response.data.items);
      })
      .catch(error => console.error('Error fetching video comments:', error));
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
        <div className="subscriptions">
          <h2>Subscriptions</h2>
          {subscriptions.map(subscription => (
            <div key={subscription.snippet.resourceId.channelId} onClick={() => handleChannelSelect(subscription.snippet.resourceId.channelId)}>
              {subscription.snippet.title}
            </div>
          ))}
        </div>

        {selectedChannel && (
          <div className="videos">
            <h2>Videos</h2>
            {videos.map(video => (
              <div key={video.id.videoId} onClick={() => handleVideoSelect(video.id.videoId)}>
                {video.snippet.title}
              </div>
            ))}
          </div>
        )}

        {selectedVideo && (
          <div className="comments">
            <h2>Comments</h2>
            {comments.map(comment => (
              <div key={comment.id}>
                <p>{comment.snippet.topLevelComment.snippet.authorDisplayName}</p>
                <p>{comment.snippet.topLevelComment.snippet.textDisplay}</p>
              </div>
            ))}
          </div>
        )}
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
