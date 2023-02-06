import React, {useState} from 'react';
import {useNavigate} from "react-router";
import {useUserTokenUpdateContext} from "../hooks/UserTokenContext";

export default function Login() {
    const [loginCreds, setLoginCreds] = useState({
        "username": "",
        "password": ""
    });

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
                setUserToken(data);
                localStorage.setItem("userToken", data)
            });
        let date = new Date();
        let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        let month = date.getMonth() < 10 ? "0" + date.getMonth() : date.getMonth();
        let dateString = date.getFullYear() + "-" + month + "-" + day;
        navigate("/" + dateString);
    }
    return (<div className="form-group">
        <h1>Login</h1>
        <label>Username: </label>
        <input type="text" className="form-control" name="username" value={loginCreds.username}
               onChange={handleChange}/>
        <br/><label>Password: </label>
        <input type="password" className="form-control" name="password" value={loginCreds.password}
               onChange={handleChange}/>
        <button style={{display: "block", margin: "auto"}} onClick={login}>Log In</button>
    </div>)
}