import axios from 'axios';

export const postLogout = async () => {
  try {
    const response = await axios.post('http://localhost:8080/logout', {}, {
      headers: {
        'Content-Type': 'application/json',
      },
      withCredentials: true,
    });
    if (response.status === 200) {
      // 로그아웃이 성공하면 로그인 페이지로 리디렉트
      // window.location.href = '/';
      console.log('로그아웃 성공');
      return true;
    } else {
      console.error('로그아웃 실패');
      return false;
    }
  } catch (error) {
    console.error('Error during logout:', error);
    return false;
  }
};