import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';

const Home = () => {
  const [userInfo, setUserInfo] = useState(null);
  const [videos, setVideos] = useState([]);

  useEffect(() => {
    const fetchUserInfo = async () => {
      try {
        const accessToken = Cookies.get('ACCESS_TOKEN');
        const response = await axios.get('http://localhost:8080/api/user', {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
          withCredentials: true,
        });
        setUserInfo(response.data);
      } catch (error) {
        console.error('사용자 정보를 가져오는 중 오류 발생', error);
      }
    };

    // const fetchVideos = async () => {
    //   try {
    //     const accessToken = Cookies.get('ACCESS_TOKEN');
    //     const response = await axios.get('http://localhost:8080/api/youtube/videos', {
    //       headers: {
    //         Authorization: `Bearer ${accessToken}`,
    //       },
    //       withCredentials: true,
    //     });
    //     setVideos(response.data);
    //   } catch (error) {
    //     console.error('동영상 정보를 가져오는 중 오류 발생', error);
    //   }
    // };

    fetchUserInfo();
    // fetchVideos();
  }, []);

  return (
    <div>
      {userInfo && (
        <div>
          <h1>{userInfo.name}님의 프로필</h1>
          <img src={userInfo.picture} alt="프로필 사진" />
          <p>Email: {userInfo.email}</p>
        </div>
      )}
      <div>
        <h2>유튜브 동영상</h2>
        {videos.map(video => (
          <div key={video.id}>
            <h3>{video.title}</h3>
            <img src={video.smThumbnail} alt={video.title} />
            <p>{video.description}</p>
          </div>
        ))}
      </div>
    </div>
  );
};

export default Home;
