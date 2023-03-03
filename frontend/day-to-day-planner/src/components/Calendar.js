import React from "react";
import Calendar from "react-calendar";
import 'react-calendar/dist/Calendar.css';
import { useNavigate } from "react-router";

export default function CustomCalendar(props) {
    const navigate = useNavigate();

    const handleChangeDate = (date) => {
        props.setDate(date);
        let day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
        let month = date.getMonth() < 9 ? "0" + (date.getMonth() + 1) : date.getMonth() +1;
        let dateString = date.getFullYear() + "-" + month + "-" + day;
        navigate("/" +  dateString);
    }
    return (
        <div className="calendar">
            <h1 className='text-center'>Date: {props.date.toLocaleDateString()}</h1>
            <div className='calendar-container'>
                <Calendar onChange={handleChangeDate} value={props.date}/>
            </div>
        </div>
    );
}