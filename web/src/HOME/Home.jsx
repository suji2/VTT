import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Cookies from 'js-cookie';
import { useNavigate } from 'react-router-dom';
import './Home.css';

const Home = () => {
  const [subscriptions, setSubscriptions] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchSubscriptions = async () => {
      try {
        const accessToken = Cookies.get('ACCESS_TOKEN');
        const response = await axios.get('http://localhost:8080/youtube/channel', {
          headers: {
            Authorization: `Bearer ${accessToken}`,
          },
          withCredentials: true,
        });

        console.log('Subscriptions response:', response.data);

        setSubscriptions(response.data); // 구독 채널 목록을 상태에 저장합니다.
      } catch (error) {
        console.error('구독 채널 정보를 가져오는 중 오류 발생', error);
      }
    };

    fetchSubscriptions();
  }, []);

  const handleChannelClick = (channelId) => {
    navigate(`/channel/${channelId}`);
  };

  return (
    <div className="home">
      <div>
        <h2>구독 채널</h2>
        <div className="subscriptions-container">
          {subscriptions.length > 0 ? (
            subscriptions.map(subscription => (
              <div key={subscription.channelId} className="subscription" onClick={() => handleChannelClick(subscription.channelId)}>
                <h3 className="subscription-title">{subscription.title}</h3>
                <img className="thumbnail-image" src={subscription.thumnailUrl} alt={subscription.title} />
              </div>
            ))
          ) : (
            <p>구독 채널이 없습니다.</p>
          )}
        </div>
      </div>
    </div>
  );
};

export default Home;
