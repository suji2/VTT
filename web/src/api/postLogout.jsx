import axios from 'axios';

export const postLogout = async () => {
  try {
    const response = await axios.post('http://localhost:8080/api/logout', {}, {
      withCredentials: true
    });
    return response.data;
  } catch (error) {
    console.error('Logout error:', error);
    throw error;
  }
};

export default postLogout;
