import React, { useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import './VideoPlayer.css';

const VideoPlayer = () => {
  const { videoId, title } = useParams();
  const [summary, setSummary] = useState("");
  const [loading, setLoading] = useState(false);

  const handleSummary = async () => {
    setLoading(true);
    try {
      const response = await axios.post('http://localhost:8080/youtube/summarize', { video_id: videoId });
      console.log('Server Response:', response.data);
      setSummary(response.data.summary ? response.data.summary : JSON.stringify(response.data));
    } catch (error) {
      console.error("Failed to fetch video summary", error);
      if (error.response) {
        console.error("Server responded with status code:", error.response.status);
      }
      setSummary("Failed to fetch video summary");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="video-player-container">
      <h2>{decodeURIComponent(title)}</h2>
      <div className="video-wrapper">
        <iframe
          width="560"
          height="315"
          src={`https://www.youtube.com/embed/${videoId}`}
          frameBorder="0"
          allow="accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture"
          allowFullScreen
          title="YouTube video player"
        ></iframe>
      </div>
      <button onClick={handleSummary} className="summary-button">요약하기</button>
      {loading ? (
        <p>로딩 중...</p>
      ) : (
        summary && <p className="video-summary">{summary}</p>
      )}
    </div>
  );
};

export default VideoPlayer;
