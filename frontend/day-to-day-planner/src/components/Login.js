import React, {useState} from 'react';
import {useNavigate} from "react-router";
import {useUserTokenUpdateContext} from "../hooks/UserTokenContext";
import {BASE_URI} from "../constants/constants";
import {getFormattedDate} from "../utils/dateUtils";

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
        fetch( BASE_URI + "/api/auth/login", {
            method: "POST",
            headers: {
                authorization: "Basic " + btoa(loginCreds.username + ":" + loginCreds.password)
            }
        }).then(res => res.text())
            .then(data => {
                if (data) {
                    setUserToken(data);
                    localStorage.setItem("userToken", data);
                    let dateString = getFormattedDate(new Date());
                    navigate("/" + dateString);
                } else {
                    setError("The credentials are not correct.");
                }
            });
    }
    return (<div className="form-group">
        <h1>Login</h1>
        {error && <div style={{margin: "auto", color: "white", background: "red", borderRadius: "10px", padding: "10px", width: "50%"}}
                       className="error">{error}</div>}
        <label>Username: </label>
        <input type="text" className="form-control" name="username" value={loginCreds.username}
               onChange={handleChange} required />
        <br/><label>Password: </label>
        <input type="password" className="form-control" name="password" value={loginCreds.password}
               onChange={handleChange} required />
        <button className="save-btn" onClick={login}>Log In</button>
    </div>)
}