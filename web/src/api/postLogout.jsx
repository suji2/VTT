import axios from 'axios';

export const postLogout = async () => {
  try {
    const csrfToken = getCsrfToken(); // CSRF 토큰을 가져오는 함수 호출
    await axios.post('http://localhost:8080/v1/oauth/logout', {}, {
      withCredentials: true,
      headers: {
        'X-XSRF-TOKEN': csrfToken
      }
    });
    window.location.href = '/';
  } catch (error) {
    console.error('Logout failed:', error);
  }
};

// CSRF 토큰을 쿠키에서 가져오는 함수
const getCsrfToken = () => {
  const match = document.cookie.match(new RegExp('(^| )XSRF-TOKEN=([^;]+)'));
  if (match) {
    return match[2];
  } else {
    console.error('CSRF token not found');
    return '';
  }
};
