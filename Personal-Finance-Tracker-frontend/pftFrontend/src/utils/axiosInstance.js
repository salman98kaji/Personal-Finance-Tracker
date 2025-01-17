import axios from 'axios';
import { API_BASE_URL } from '../api/api';

const token = localStorage.getItem("jwtToken");
console.log("Token:", token);

const axiosInstance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
    }
});

export default axiosInstance;