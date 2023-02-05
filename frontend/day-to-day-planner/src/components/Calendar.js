import React, {useState} from "react";
import Calendar from "react-calendar";
import 'react-calendar/dist/Calendar.css';

export default function CustomCalendar() {
    const [date, setDate] = useState(new Date());

    return (
        <div className="calendar">
            <h1 className='text-center'>Date: {date.toLocaleDateString()}</h1>
            <div className='calendar-container'>
                <Calendar onChange={setDate} value={date}/>
            </div>
        </div>
    );

}