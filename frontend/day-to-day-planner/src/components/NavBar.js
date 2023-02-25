import React from "react";
import {Link} from "react-router-dom";
import {useUserTokenUpdateContext} from "../hooks/UserTokenContext";
import { getFormattedDate } from "../utils/dateUtils";

export default function NavBar() {
    const setUserToken = useUserTokenUpdateContext();
    const logout = () => {
        localStorage.removeItem("userToken");
        setUserToken();
    }

    return (<nav className="nav">
        <div className="home"><Link to={localStorage.getItem("userToken") ? "/" + getFormattedDate(new Date()) : "/login"}>Day To Day Planner</Link></div>
        <ul className="links">
            {localStorage.getItem("userToken") ? <>
                    <li><Link to={"/profile"}>Profile</Link></li>
                    <li><Link to={"/mood-overview"}>Mood Overview</Link></li>
                    <li><Link to={"/login"} onClick={logout}>Logout</Link></li>
                </> :
                <>
                    <li><Link to={"/login"}>Login</Link></li>
                    <li><Link to={"/register"}>Register</Link></li>
                </>}
        </ul>
    </nav>);
}