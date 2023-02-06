import React, {useState} from 'react';
import { useNavigate } from 'react-router';

export default function Register() {
    const [userData, setUserData] = useState({
        "username": "",
        "password": "",
        "firstName": "",
        "lastName": "",
        "email": ""
    });

    const handleChange = (event) => {
        setUserData(prev => {
            return {
            ...prev,
            [event.target.name] : event.target.value
            }
        })
    }
    const navigate = useNavigate();
    const register = () => {
            fetch("http://localhost:8080/api/auth/register", {
                method: "POST",
                body: userData
            }).then(data => console.log(data))
            .catch(err => console.log(err));
        navigate("/login");
    }
    return (<div className="form-group">
    <h1>Login</h1>
        <label>First name: </label>
        <input type="text" className="form-control" name="firstName" value={userData.firstName} onChange={handleChange} />
        <br /><label>Last name: </label> 
        <input type="text" className="form-control" name="lastName" value={userData.lastName} onChange={handleChange} />
        <br /><label>E-mail: </label>
        <input type="email" className="form-control" name="email" value={userData.email} onChange={handleChange} />
        <br /><label>Username: </label>
        <input type="text" className="form-control" name="username" value={userData.username} onChange={handleChange} />
        <br /><label>Password: </label>
        <input type="password" className="form-control" name="password" value={userData.password} onChange={handleChange} />
        <button style={{display: "block", margin: "auto"}} onClick={register}>Register</button>
    </div>)
}