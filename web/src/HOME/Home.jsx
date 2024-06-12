import React, { useState, useEffect } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';
import './Home.css';

function Home() {
  const [subscriptions, setSubscriptions] = useState([]);
  const [selectedChannel, setSelectedChannel] = useState(null);
  const [videos, setVideos] = useState([]);
  const [selectedVideo, setSelectedVideo] = useState(null);
  const navigate = useNavigate();

  useEffect(() => {
    const token = Cookies.get('ACCESS_TOKEN');
    if (token) {
      fetchSubscriptions(token);
    }
  }, []);

  const fetchSubscriptions = async (token) => {
    try {
      const response = await axios.get('/youtube/channel', {
        headers: {
          'Authorization': `Bearer ${token}`
        },
        withCredentials: true
      });
      setSubscriptions(response.data.items);
    } catch (error) {
      console.error('구독 목록을 가져오는 중 오류 발생:', error);
    }
  };

  const handleChannelSelect = (channelId) => {
    setSelectedChannel(channelId);
    const token = Cookies.get('ACCESS_TOKEN');
    axios.get('/youtube/channelvideos', {
      headers: {
        'Authorization': `Bearer ${token}`
      },
      params: { channelId: channelId, nextPageToken: '' },
      withCredentials: true
    })
      .then(response => {
        setVideos(response.data.items);
      })
      .catch(error => console.error('채널 비디오를 가져오는 중 오류 발생:', error));
  };

  const handleVideoSelect = (videoId) => {
    setSelectedVideo(videoId);
    const token = Cookies.get('ACCESS_TOKEN');
    axios.get('/youtube/detail', {
      headers: {
        'Authorization': `Bearer ${token}`
      },
      params: { videoId: videoId },
      withCredentials: true
    })
      .then(response => {
        // 비디오 상세 정보 저장 (원하는 경우)
      })
      .catch(error => console.error('비디오 세부 정보를 가져오는 중 오류 발생:', error));
  };

  return (
    <div className="home">
      <main className="main-content">
        <div className="subscriptions">
          <h2>구독 채널</h2>
          {subscriptions.map(subscription => (
            <div key={subscription.snippet.resourceId.channelId} onClick={() => handleChannelSelect(subscription.snippet.resourceId.channelId)}>
              {subscription.snippet.title}
            </div>
          ))}
        </div>

        {selectedChannel && (
          <div className="videos">
            <h2>비디오</h2>
            {videos.map(video => (
              <div key={video.id.videoId} onClick={() => handleVideoSelect(video.id.videoId)}>
                {video.snippet.title}
              </div>
            ))}
          </div>
        )}

        {selectedVideo && (
          <div className="video-details">
            <h2>비디오 상세 정보</h2>
            {/* 비디오 상세 정보 표시 (원하는 경우) */}
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
