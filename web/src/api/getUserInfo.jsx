import axios from './axiosConfig';

const getUserInfo = async () => {
  try {
    const response = await axios.get('/api/user');
    return response.data;
  } catch (error) {
    console.error('Error fetching user info:', error);
    return null;
  }
};

export default getUserInfo;
