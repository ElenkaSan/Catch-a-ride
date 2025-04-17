import axios from 'axios';

const instance = axios.create({
  baseURL: 'http://localhost:8080',
});

instance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('token');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
      console.log('Attached token to request:', token);
    } else {
      console.log('No token found in localStorage.');
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default instance; 