
import { useRef, useState, useEffect } from "react";
import axios from 'axios';
import { API_BASE_URL } from "../api/api";
import { Link, useNavigate } from "react-router-dom";

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;

function Register() {

//The useRef hook is used in React to create a reference to a DOM element or a mutable value that persists across renders without triggering a re-render.
  const userRef = useRef();//This reference is used to point to the username input field in the DOM.
  const errRef = useRef();//This reference is intended to manage the error message container in the DOM.

//useState is used to define and manage state variables in functional components.
  const [user, setUser] = useState(''); //To store and update the username entered by the user.
  const[validName, setValidName] = useState(false); //To track if the username is valid based on a regular expression (USER_REGEX).
  const [userFocus, setUserFocus] = useState(false);//To track whether the username input field is currently focused.

  const [email, setEmail] = useState('');
  const [emailFocus, setEmailFocus] = useState(false);

  const [pwd, setPwd] = useState('');//To store and update the password entered by the user.
  const [validPwd, setValidPwd] = useState(false);
  const [pwdFocus, setPwdFocus] = useState(false);//To track whether the password input field is currently focused.

  const [matchPwd, setMatchPwd] = useState('');// To store and update the password confirmation entered by the user.
  const [validMatch, setValidMatch] = useState(false);//To track if the confirmed password matches the original password.
  const [matchFocus, setMatchFocus] = useState(false);

  const [errMsg, setErrMsg] = useState('');//To store and update error messages related to form submission or validation.
  const [success, setSuccess] = useState(false);//To track whether the registration process was successful.

  const navigate = useNavigate();

//useEffect is used to perform side effects in functional components. It runs after the component renders or updates.
  //Automatically focuses the username input field when the component is first rendered.
  useEffect(() => {
    userRef.current.focus();
  },[]) //ensures this effect runs only once (on mount).

  //Validates the username whenever the user state changes.
  useEffect(() => {
    setValidName(USER_REGEX.test(user));
  },[user])

  useEffect(() => {
    setValidPwd(PWD_REGEX.test(pwd));//Validates the password whenever pwd changes using PWD_REGEX.
    setValidMatch(pwd == matchPwd);
  },[pwd, matchPwd])

  //Clears any existing error messages whenever user, pwd, or matchPwd changes.
  useEffect(() => {
    setErrMsg('');
  },[user, pwd, matchPwd])

  const handleSubmit = async (e) => {
    e.preventDefault();
    const v1 = USER_REGEX.test(user);
    const v2 =PWD_REGEX.test(pwd);
    
    if(!v1 || !v2){
      setErrMsg("Inavlid Entry");
      return;
    }

    try {
      const response = await axios.post(`${API_BASE_URL}/users/register`, JSON.stringify(
        {
          username:user, 
          email:email, 
          password:pwd
        }),
      {
        headers: {'Content-Type':'application/json'},
      }
    );
        console.log(response?.data);
        console.log(response?.accessToken);
        console.log(JSON.stringify(response)); 
        setSuccess(true);
        setUser('');
        setPwd('');
        setMatchPwd('');

    } catch (err) {
        if (!err?.response) {
          setErrMsg('No Server Response');
      } else if (err.response?.status === 409) {
          setErrMsg('Username Taken');
      } else {
          setErrMsg('Registration Failed')
      }
      errRef.current.focus();
    }
  }

  return (
    <>
      {success ? (
        <section className="flex flex-col items-center justify-center h-screen">
          <h1 className="text-2xl font-bold text-green-500">Success!</h1>
          <p>
          <Link 
              to="/login" // Navigate to the login route
              className="text-blue-500 underline hover:text-blue-700"
            >
              Sign In
            </Link>
          </p>
        </section>
      ) : (
        <section className="flex flex-col items-center justify-center h-screen bg-blue-400">
          <div className="bg-blue-700 p-6 rounded-md shadow-md w-80">
            <p
              ref={errRef}
              className={`text-red-500 text-sm ${errMsg ? "block" : "hidden"}`}
              aria-live="assertive"
            >
              {errMsg}
            </p>
            <h1 className="text-2xl font-bold text-center mb-4">Register</h1>
            <form onSubmit={handleSubmit} className="space-y-4">
              <div>
                <label htmlFor="username" className="block text-left text-white text-sm font-medium">Username:</label>
                <input
                  type="text"
                  id="username"
                  ref={userRef}
                  onChange={(e) => setUser(e.target.value)}
                  value={user}
                  required
                  onFocus={() => setUserFocus(true)}
                  onBlur={() => setUserFocus(false)}
                  className="w-full border border-gray-300 rounded-md px-2 py-1 focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
              <div>
                <label htmlFor="email" className="block text-left text-white text-sm font-medium">Email:</label>
                <input
                  type="text"
                  id="email"
                  ref={userRef}
                  onChange={(e) => setEmail(e.target.value)}
                  value={email}
                  required
                  onFocus={() => setEmailFocus(true)}
                  onBlur={() => setEmailFocus(false)}
                  className="w-full border border-gray-300 rounded-md px-2 py-1 focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
              <div>
                <label htmlFor="password" className="block text-left text-white text-sm font-medium">Password:</label>
                <input
                  type="password"
                  id="password"
                  onChange={(e) => setPwd(e.target.value)}
                  value={pwd}
                  required
                  onFocus={() => setPwdFocus(true)}
                  onBlur={() => setPwdFocus(false)}
                  className="w-full border border-gray-300 rounded-md px-2 py-1 focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
              <div>
                <label htmlFor="confirm_pwd" className="block text-left text-white text-sm font-medium">Confirm Password:</label>
                <input
                  type="password"
                  id="confirm_pwd"
                  onChange={(e) => setMatchPwd(e.target.value)}
                  value={matchPwd}
                  required
                  onFocus={() => setMatchFocus(true)}
                  onBlur={() => setMatchFocus(false)}
                  className="w-full border border-gray-300 rounded-md px-2 py-1 focus:outline-none focus:ring-2 focus:ring-blue-500"
                />
              </div>
              <button
                disabled={!validName || !validPwd || !validMatch}
                className={`w-full py-2 mt-4 text-white rounded-md ${!validName || !validPwd || !validMatch ? "bg-gray-400 cursor-not-allowed" : "bg-blue-500 hover:bg-blue-600"}`}
              >
                Sign Up
              </button>
            </form>
            <p className="text-sm mt-4 text-center">
              Already registered? 
              <Link 
                to="/login" // Navigate to the login route
                className="underline text-black"
              >
                Sign In
              </Link>
            </p>
          </div>
        </section>
      )}
    </>
  )
}

export default Register