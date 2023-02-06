import React from "react";

const UserTokenContext = React.createContext();
const UserTokenUpdateContext = React.createContext();

export function useUserTokenContext() {
    return React.useContext(UserTokenContext);
}

export function useUserTokenUpdateContext() {
    return React.useContext(UserTokenUpdateContext);
}

export function resetToken() {}

export function UserTokenContextProvider({ children }) {
    const [userToken, setUserToken] = React.useState(
        localStorage.getItem("userToken")
    );
    const setToken = (token) => setUserToken(token);
    return (
        <UserTokenContext.Provider value={userToken}>
            <UserTokenUpdateContext.Provider value={setToken}>
                {children}
            </UserTokenUpdateContext.Provider>
        </UserTokenContext.Provider>
    );
}
