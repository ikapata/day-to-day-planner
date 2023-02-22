import './App.css';
import React from "react";
import {createBrowserRouter, RouterProvider} from "react-router-dom";

import MainLayout from "./components/MainLayout";
import DayEntry from "./components/DayEntry";
import Profile from "./components/Profile";
import Login from "./components/Login";
import Register from "./components/Register";
import {UserTokenContextProvider} from "./hooks/UserTokenContext";
import MoodOverview from './components/MoodOverview';


function App() {
    const router = createBrowserRouter([
        {
            path: "/",
            element: <MainLayout/>,
            children: [
                {
                    path: "/:date",
                    element: <DayEntry/>
                },
                {
                    path: "/profile",
                    element: <Profile/>
                },
                {
                    path: "/mood-overview",
                    element: <MoodOverview />
                },
                {
                    path: "/login",
                    element: <Login/>
                },
                {
                    path: "/register",
                    element: <Register/>
                }
            ]
        }
    ])
    return (
        <React.StrictMode>
            <UserTokenContextProvider>
                <RouterProvider router={router}/>
            </UserTokenContextProvider>
        </React.StrictMode>
    );
}

export default App;
