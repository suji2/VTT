import { useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

const PostLoginToken = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URLSearchParams(window.location.search);
    const token = params.get('token');

    if (token) {
      localStorage.setItem('accessToken', token);
      navigate('/mypage');
    }
  }, [navigate]);

  return null;
};

export default PostLoginToken;