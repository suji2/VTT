import React, { useEffect, useState } from 'react';
import axios from 'axios';

const GetUserInfo = () => {
  const [userInfo, setUserInfo] = useState(null);

  useEffect(() => {
    const fetchUserInfo = async () => {
      const token = localStorage.getItem('accessToken');
      if (token) {
        try {
          const response = await axios.get('http://localhost:8080/api/user', {
            headers: {
              Authorization: `Bearer ${token}`,
            },
          });
          setUserInfo(response.data);
        } catch (error) {
          console.error('사용자 정보를 가져오는 중 오류 발생', error);
        }
      }
    };

    fetchUserInfo();
  }, []);

  if (!userInfo) return <div>로딩 중...</div>;

  return (
    <div>
      <h1>사용자 정보</h1>
      <p>이름: {userInfo.name}</p>
      <p>이메일: {userInfo.email}</p>
      <img src={userInfo.picture} alt="프로필" />
    </div>
  );
};

export default GetUserInfo;