import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080', // 백엔드 서버의 실제 URL
  withCredentials: true,
});

export default instance;
