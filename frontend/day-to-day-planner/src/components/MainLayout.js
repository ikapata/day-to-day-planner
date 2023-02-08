import React, {useEffect} from "react";
import {Outlet, useNavigate} from "react-router";
import NavBar from "./NavBar";
import {useUserTokenContext} from "../hooks/UserTokenContext";

export default function MainLayout() {
    let navigate = useNavigate();
    let userTokenContext = useUserTokenContext();
    useEffect(() => {
        if (localStorage.getItem("userToken")) {
            navigate("/" + new Date().toLocaleDateString('zh-Hans-CN', {
                year: "numeric",
                month: "2-digit",
                day: "2-digit",
            }).replaceAll("/", "-"))
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
