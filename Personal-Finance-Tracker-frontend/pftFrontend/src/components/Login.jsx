import { useState } from 'react'
import axios from 'axios';
import { API_BASE_URL } from '../api/api';
import {useNavigate} from 'react-router-dom';


function Login() {

    const [user, setUser] = useState('');
    const [pwd, setPwd] = useState('');
    const [error, setError] = useState('');
    const navigate = useNavigate();

    const handleLogin = async(e) => {
        e.preventDefault();

        try {
            // Send a POST request to your backend API for login
            const response = await axios.post(`${API_BASE_URL}/users/login`,{
                    username:user,
                    password:pwd 
            });

            // Extract JWT token from the response
            const token = response.data

            // Store the token in localStorage (or sessionStorage)
            localStorage.setItem('jwtToken', token);

            // Clear any previous error messages
            setError('');

            //  Redirect to the dashboard or another protected route
            navigate('/dashboard');

        } catch (error) {
            console.error('Login failed:', error);

            // Display an error message
            if (error.response && error.response.data) {
                setError(error.response.data.message || 'Login failed. Please try again.');
            } else {
                setError('Login failed. Please check your credentials.');
            }
        }
    }

    return (
        <section className="flex flex-col items-center justify-center h-screen bg-blue-400">
            <div className="bg-blue-700 p-6 rounded-md shadow-md w-80">
                <h1 className="text-2xl font-bold text-center mb-4">Login</h1>
                <form onSubmit={handleLogin} className="space-y-4">
                    <div>
                        <label htmlFor="username" className="block text-left text-white text-sm font-medium">Username:</label>
                        <input 
                            type="text"
                            id='username'
                            value={user}
                            onChange={(e) => setUser(e.target.value)}
                            required
                            className="w-full border border-gray-300 rounded-md px-2 py-1 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                    </div>
                    <div>
                        <label htmlFor="password" className="block text-left text-white text-sm font-medium">Password:</label>
                        <input 
                            type="text"
                            id='password'
                            value={pwd}
                            onChange={(e) => setPwd(e.target.value)}
                            required
                            className="w-full border border-gray-300 rounded-md px-2 py-1 focus:outline-none focus:ring-2 focus:ring-blue-500"
                        />
                    </div>
                    <button className='w-full py-2 mt-4 text-white rounded-md "bg-gray-400 hover:bg-blue-600'>
                        Sign In
                    </button>
                </form>
            </div>
        </section>
    )
}

export default Login