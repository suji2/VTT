import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import './login.css';

function Login({ isLogin, setIsLogin }) {
  const navigate = useNavigate();

  useEffect(() => {
    checkLoginStatus();
  }, []);

  const checkLoginStatus = async () => {
    try {
      const response = await axios.get("https://192.168.34.29:3000/user", { withCredentials: true });
      if (response.data) {
        localStorage.setItem('accessToken', response.data.token); // 액세스 토큰 저장
        setIsLogin(true);
        navigate('/'); // 로그인 성공 시 Home으로 이동
      }
    } catch (error) {
      console.error('로그인 상태를 확인하는 데 오류가 발생했습니다:', error);
    }
  };

  return (
    <div className="login">
      {/* 로그인 상태 확인 및 리디렉션 로직만 유지 */}
    </div>
  );
}

export default Login;
