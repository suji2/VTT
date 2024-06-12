import axios from 'axios';
import Cookies from 'js-cookie';

export const postLogout = async () => {
  try {
    await axios.post('http://localhost/logout', {}, {
      headers: {
        'Authorization': `Bearer ${Cookies.get('ACCESS_TOKEN')}`,
      },
      withCredentials: true,
    });
    Cookies.remove('ACCESS_TOKEN');
  } catch (error) {
    console.error('Error during logout:', error);
  }
};