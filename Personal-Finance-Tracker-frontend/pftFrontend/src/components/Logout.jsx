
import {useDispatch} from 'react-redux'
import { logout } from '../redux/slices/authSlice';
import { useNavigate } from 'react-router-dom'; 

function Logout() {
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const logoutHandler = () => {
        dispatch(logout());
        localStorage.removeItem("jwtToken");
        navigate('/login');
    }

  return (
    <button onClick={logoutHandler} className='bg-red-500 text-white px-4 py-2 rounded hover:bg-red-700'>
        Logout
    </button>
  )
}

export default Logout