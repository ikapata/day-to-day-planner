import React, {useState} from 'react';
import {useNavigate} from "react-router";
import {useUserTokenUpdateContext} from "../hooks/UserTokenContext";

export default function Login() {
    const [loginCreds, setLoginCreds] = useState({
        "username": "",
        "password": ""
    });

    const [error, setError] = useState("");
    const navigate = useNavigate();
    const setUserToken = useUserTokenUpdateContext();

    const handleChange = (event) => {
        setLoginCreds(prev => {
            return {
                ...prev,
                [event.target.name]: event.target.value
            }
        })
    }
    const login = () => {
        fetch("http://localhost:8080/api/auth/login", {
            method: "POST",
            headers: {
                authorization: "Basic " + btoa(loginCreds.username + ":" + loginCreds.password)
            }
        }).then(res => res.text())
            .then(data => {
                if (data) {
                    setUserToken(data);
                    localStorage.setItem("userToken", data);
                    let dateString = new Date().toLocaleDateString('zh-Hans-CN', {
                        year: "numeric",
                        month: "2-digit",
                        day: "2-digit",
                    }).replaceAll("/", "-");
                    navigate("/" + dateString);
                } else {
                    setError("The credentials are not correct.");
                }
            });
    }
    return (<div className="form-group">
        <h1>Login</h1>
        {error && <div style={{color: "white", background: "red", borderRadius: "10px", padding: "10px", width: "50%"}}
                       className="error">{error}</div>}
        <label>Username: </label>
        <input type="text" className="form-control" name="username" value={loginCreds.username}
               onChange={handleChange}/>
        <br/><label>Password: </label>
        <input type="password" className="form-control" name="password" value={loginCreds.password}
               onChange={handleChange}/>
        <button className="save-btn" onClick={login}>Log In</button>
    </div>)
}