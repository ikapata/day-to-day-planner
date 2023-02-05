import React from "react";

export default function NavBar() {
    return (<nav className="nav">
        <div className="home">Day To Day Planner</div>
            <ul className="links">
                <li>Login</li>
                <li>Logout</li>
                <li>Profile</li>
            </ul>
        </nav>);
}