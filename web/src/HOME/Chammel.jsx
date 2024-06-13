import React, { useEffect, useState } from 'react';
import { Link, useParams } from 'react-router-dom';
import axios from 'axios';
import Cookies from 'js-cookie';
import './Channel.css';

const Channel = () => {
  const { channelId } = useParams();
  const [videos, setVideos] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchChannelVideos = async () => {
      try {
        const accessToken = Cookies.get('ACCESS_TOKEN');
        let allVideos = [];
        let nextPageToken = '';
        let keepFetching = true;

        while (allVideos.length < 50 && keepFetching) {
          const response = await axios.get(`http://localhost:8080/youtube/channelvideos?channelId=${channelId}&nextPageToken=${nextPageToken}`, {
            headers: {
              Authorization: `Bearer ${accessToken}`,
            },
            withCredentials: true,
          });

          const fetchedVideos = response.data;
          allVideos = [...allVideos, ...fetchedVideos];
          nextPageToken = response.data.nextPageToken || '';
          keepFetching = fetchedVideos.length > 0 && nextPageToken;

          console.log('Fetched videos:', fetchedVideos);
          console.log('Next page token:', nextPageToken);
        }

        setVideos(allVideos.slice(0, 50)); // 최신 영상 50개만 가져오기
      } catch (error) {
        console.error('채널 동영상을 가져오는 중 오류 발생', error);
      } finally {
        setLoading(false);
      }
    };

    fetchChannelVideos();
  }, [channelId]);

  if (loading) {
    return <div>로딩 중...</div>;
  }

  return (
    <div className="channel">
      <h2>동영상 목록</h2>
      <div className="videos-container">
        {videos.length > 0 ? (
          videos.map(video => (
            <Link to={`/video/${video.videoId}/${encodeURIComponent(video.title)}`} key={video.videoId} className="video">
              <img className="video-thumbnail" src={video.smThumbnail} alt={video.title} />
              <div className="video-details">
                <h3 className="video-title">{video.title}</h3>
                <p className="video-description">{video.description}</p>
                <p className="video-published">게시일: {new Date(video.publishedAt).toLocaleDateString()}</p>
              </div>
            </Link>
          ))
        ) : (
          <p>동영상이 없습니다.</p>
        )}
      </div>
    </div>
  );
};

export default Channel;
