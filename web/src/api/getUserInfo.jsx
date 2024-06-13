import axios from './axiosConfig';

const getUserInfo = async () => {
  try {
    const response = await axios.get('http://localhost:8080/api/user');
    return response.data;
  } catch (error) {
    console.error('Error fetching user info:', error);
    return null;
  }
};

export default getUserInfo;
