import React, {useState} from "react";
import Calendar from "react-calendar";
import 'react-calendar/dist/Calendar.css';
import { useNavigate } from "react-router";

export default function CustomCalendar(props) {
    const navigate = useNavigate();

    const handleChangeDate = (date) => {
        props.setDate(date);
        navigate("/" +  date.toLocaleDateString().replaceAll("/", "-"));
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