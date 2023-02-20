import React from "react";
import {Link} from "react-router-dom";
import {useUserTokenUpdateContext} from "../hooks/UserTokenContext";

export default function NavBar() {
    const setUserToken = useUserTokenUpdateContext();
    const logout = () => {
        localStorage.removeItem("userToken");
        setUserToken();

    }
    return (<nav className="nav">
        <div className="home">Day To Day Planner</div>
        <ul className="links">
            {localStorage.getItem("userToken") ? <>
                    <li><Link to={"/profile"}>Profile</Link></li>
                    <li><Link to={"/login"} onClick={logout}>Logout</Link></li>
                </> :
                <>
                    <li><Link to={"/login"}>Login</Link></li>
                    <li><Link to={"/register"}>Register</Link></li>
                </>}
        </ul>
    </nav>);
}