import React from "react";
import {Outlet} from "react-router";
import NavBar from "./NavBar";

export default function MainLayout() {
    return (
        <>
            <NavBar />
            <Outlet />
        </>
    )
}
