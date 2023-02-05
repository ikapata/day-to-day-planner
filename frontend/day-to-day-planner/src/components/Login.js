import React, {useState} from 'react';

export default function Login() {
    const [loginCreds, setLoginCreds] = useState({
        "username": "",
        "password": ""
    });

    const handleChange = (event) => {
        setLoginCreds(prev => {
            return {
            ...prev,
            [event.target.name] : event.target.value
            }
        })
    }
    const login = () => {
        // fetch to login 

        // save JWT token in local storage
    }
    return (<div className="form-group">
    <h1>Login</h1>
        <label>Username: </label>
        <input type="text" className="form-control" name="username" value={loginCreds.username} onChange={handleChange} />
        <br /><label>Password: </label>
        <input type="password" className="form-control" name="password" value={loginCreds.password} onChange={handleChange} />
        <button style={{display: "block", margin: "auto"}} onClick={login}>Log In</button>
    </div>)
}