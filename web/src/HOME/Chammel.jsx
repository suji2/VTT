import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';
import './Channel.css';

const Channel = () => {
  const { channelId } = useParams();
  const [videos, setVideos] = useState([]);

  useEffect(() => {
    const fetchChannelVideos = async () => {
      try {
        const accessToken = Cookies.get('ACCESS_TOKEN');
        const response = await axios.get(`http://localhost:8080/youtube/channelvideos?channelId=${channelId}`, {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
          withCredentials: true,
        });

        console.log('Channel videos response:', response.data);

        setVideos(response.data);
      } catch (error) {
        console.error('채널 동영상을 가져오는 중 오류 발생', error);
      }
    };

    fetchChannelVideos();
  }, [channelId]);

  return (
    <div className="channel">
      <h2>동영상 목록</h2>
      <div className="videos-container">
        {videos.length > 0 ? (
          videos.map(video => (
            <div key={video.videoId} className="video">
              <img className="video-thumbnail" src={video.smThumbnail} alt={video.title} />
              <div className="video-details">
                <h3 className="video-title">{video.title}</h3>
                <p className="video-description">{video.description}</p>
                <p className="video-published">게시일: {new Date(video.publishedAt).toLocaleDateString()}</p>
              </div>
            </div>
          ))
        ) : (
          <p>동영상이 없습니다.</p>
        )}
      </div>
    </div>
  );
};

export default Channel;
