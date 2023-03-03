import React, {useState} from 'react';
import {useNavigate} from 'react-router';
import {BASE_URI} from "../constants/constants";

export default function Register() {
    const [userData, setUserData] = useState({
        "username": "", "password": "", "firstName": "", "lastName": "", "email": ""
    });
    const [error, setError] = useState("");

    const handleChange = (event) => {
        setUserData(prev => {
            return {
                ...prev, [event.target.name]: event.target.value
            }
        })
    }
    const navigate = useNavigate();
    const register = (event) => {
        event.preventDefault();
        if (userData.email === "" || userData.username === "" || userData.password === "") {
            setError("Please fill in all fields");
            return;
        }
        let headers = new Headers();
        headers.append("Content-Type", "application/json");

        const raw = JSON.stringify(userData);

        const requestOptions = {
            method: 'POST', headers: headers, body: raw, redirect: 'follow', cors: "no-cors", referrer: "no-referrer"
        };

        fetch(BASE_URI + "/api/auth/register", requestOptions)
            .then(response =>   (response.status === 200) ? navigate("/login") : setError("This email or username is already in use!"))
            .catch(error => setError(error.message));
    }
    return (<div className="form-group">
        <h1>Register</h1>
        {error && <div style={{margin: "auto", color: "white", background: "red", borderRadius: "10px", padding: "10px", width: "50%"}}
                       className="error">{error}</div>}
        <form>
        <br/><label>E-mail: </label>
        <input type="email" className="form-control" name="email" value={userData.email} onChange={handleChange} required={true} />
        <br/><label>Username: </label>
        <input type="text" className="form-control" name="username" value={userData.username} onChange={handleChange} required={true} />
        <br/><label>Password: </label>
        <input type="password" className="form-control" name="password" value={userData.password} required={true}
               onChange={handleChange}/>
        <input type="submit" className="save-btn" onClick={register} value="Register" />
        </form>
    </div>)
}