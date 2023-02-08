import React, {useState} from 'react';
import {useNavigate} from 'react-router';

export default function Register() {
    const [userData, setUserData] = useState({
        "username": "", "password": "", "firstName": "", "lastName": "", "email": ""
    });

    const handleChange = (event) => {
        setUserData(prev => {
            return {
                ...prev, [event.target.name]: event.target.value
            }
        })
    }
    const navigate = useNavigate();
    const register = () => {
        let headers = new Headers();
        headers.append("Content-Type", "application/json");

        const raw = JSON.stringify(userData);

        const requestOptions = {
            method: 'POST', headers: headers, body: raw, redirect: 'follow', cors: "no-cors", referrer: "no-referrer"
        };

        fetch("http://localhost:8080/api/auth/register", requestOptions)
            .then(result => navigate("/login"))
            .catch(error => console.log('error', error));
    }
    return (<div className="form-group">
        <h1>Register</h1>
        <label>First name: </label>
        <input type="text" className="form-control" name="firstName" value={userData.firstName} required
               onChange={handleChange}/>
        <br/><label>Last name: </label>
        <input type="text" className="form-control" name="lastName" value={userData.lastName} onChange={handleChange} required/>
        <br/><label>E-mail: </label>
        <input type="email" className="form-control" name="email" value={userData.email} onChange={handleChange} required/>
        <br/><label>Username: </label>
        <input type="text" className="form-control" name="username" value={userData.username} onChange={handleChange} required/>
        <br/><label>Password: </label>
        <input type="password" className="form-control" name="password" value={userData.password} required
               onChange={handleChange}/>
        <button className="save-btn" onClick={register}>Register</button>
    </div>)
}