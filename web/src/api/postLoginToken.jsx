import React, { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from './axiosConfig';

const PostLoginToken = ({ setIsLogin }) => {
  const navigate = useNavigate();

  useEffect(() => {
    const fetchToken = async () => {
      try {
        const response = await axios.get('/api/user');
        localStorage.setItem('accessToken', response.data.accessToken);
        setIsLogin(true);
        navigate('/');
      } catch (error) {
        console.error('Error fetching token:', error);
        setIsLogin(false);
        navigate('/login');
      }
    };
    fetchToken();
  }, [setIsLogin, navigate]);

  return <div>Loading...</div>;
};

export default PostLoginToken;
