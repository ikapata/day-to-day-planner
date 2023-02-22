import React, {useEffect} from "react";
import {Outlet, useNavigate} from "react-router";
import NavBar from "./NavBar";
import {useUserTokenContext} from "../hooks/UserTokenContext";
import { getFormattedDate } from "../utils/dateUtils";

export default function MainLayout() {
    let navigate = useNavigate();
    let userTokenContext = useUserTokenContext();
    useEffect(() => {
        if (localStorage.getItem("userToken")) {
            navigate("/" + getFormattedDate(new Date()));
        } else {
            navigate("/login");
        }
    }, [userTokenContext])
    return (
        <>
            <NavBar />
            <Outlet />
        </>
    )
}
