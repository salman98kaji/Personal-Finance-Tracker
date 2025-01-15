
import { useRef, useState, useEffect } from "react";
import axios from 'axios';

const USER_REGEX = /^[A-z][A-z0-9-_]{3,23}$/;
const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}$/;
const REGISTER_URL = '/register';

function Register() {

//The useRef hook is used in React to create a reference to a DOM element or a mutable value that persists across renders without triggering a re-render.
  const userRef = useRef();//This reference is used to point to the username input field in the DOM.
  const errRef = useRef();//This reference is intended to manage the error message container in the DOM.

//useState is used to define and manage state variables in functional components.
  const [user, setUser] = useState(''); //To store and update the username entered by the user.
  const[validName, setValidName] = useState(false); //To track if the username is valid based on a regular expression (USER_REGEX).
  const [userFocus, setUserFocus] = useState(false);//To track whether the username input field is currently focused.

  const [pwd, setPwd] = useState('');//To store and update the password entered by the user.
  const [validPwd, setValidPwd] = useState(false);
  const [pwdFocus, setPwdFocus] = useState(false);//To track whether the password input field is currently focused.

  const [matchPwd, setMatchPwd] = useState('');// To store and update the password confirmation entered by the user.
  const [validMatch, setValidMatch] = useState(false);//To track if the confirmed password matches the original password.
  const [matchFocus, setMatchFocus] = useState(false);

  const [errMsg, setErrMsg] = useState('');//To store and update error messages related to form submission or validation.
  const [success, setSuccess] = useState(false);//To track whether the registration process was successful.

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
      const response = await axios.post(REGISTER_URL, JSON.stringify({user, pwd}),
        {
          headers: { 'Content-Type': 'application/json' },
          withCredentials: true
        });
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
        <section>
          <h1>Success!</h1>
          <p>
            <a href="#">Sign In</a>
          </p>
        </section>
      ):(
        <section>
          <p ref={errRef} className={errMsg ? "errmsg" : "offscreen"}></p>
          <h1>Register</h1>
          <form onSubmit={handleSubmit}>

            <label htmlFor="username">Username:</label>
            <input
              type="text" 
              id="username"
              ref={userRef}//Focuses this field when the component loads.
              onChange={(e) => setUser(e.target.value)}
              value={user}
              required
              onFocus={() => setMatchFocus(true)}
              onBlur={() => setMatchFocus(false)}
            />

            <label htmlFor="password">Password:</label>
            <input
              type="password"
              id="password"
              onChange={(e) => setPwd(e.target.value)}
              value={pwd}
              required
              onFocus={() => setPwdFocus(true)}
              onBlur={() => setPwdFocus(false)}
            />

            <label htmlFor="confirm_pwd">Confirm Password:</label>
            <input
              type="password"
              id="confirm_pwd"
              onChange={(e) => setMatchPwd(e.target.value)}
              value={matchPwd}
              required 
              onFocus={() => setMatchFocus(true)}
              onBlur={() => setMatchFocus(false)}
            />

            <button disabled={!validName || !validPwd || !validMatch ? true : false}>Sign Up</button>
          </form>
          <p>Already registered ?
            <br/>
            <span>
              {}
              <a href="#">Sign In</a>
            </span>
          </p>
        </section>
      )}
    </>
  )
}

export default Register