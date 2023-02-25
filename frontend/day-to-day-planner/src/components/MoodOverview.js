import React, { useEffect } from 'react';
import { Calendar, CalendarControls } from 'react-yearly-calendar';
import { BASE_URI } from '../constants/constants';
import {getFormattedDate} from '../utils/dateUtils';
import { useNavigate } from'react-router-dom';

export default function MoodOverview() {
    const navigate = useNavigate();

    const [year, setYear] = React.useState(new Date().getFullYear());
    function onDatePicked(date) {
        let dateFormatted = getFormattedDate(date._d);
        navigate("/" + dateFormatted);
      }

    const [dateMoods, setDateMoods] = React.useState({});

    useEffect(() => {
        const headers = new Headers();
            headers.append("Content-Type", "application/json");
            headers.append("authorization", "Bearer " + localStorage.getItem("userToken"));
            let requestOptions = {
                method: 'GET',
                headers: headers,
                redirect: 'follow',
                referer: "no-referer"
            };

            fetch(BASE_URI + "/api/entries/date-moods/" + year, requestOptions)
                .then(response => response.json())
                .then(data => {
                    setDateMoods(data);
                })
                .catch(error => {
                    setDateMoods({});
                });
    }, [year])
    
    return <>
        <CalendarControls
                year={year}
                onPrevYear={() => setYear(year - 1)}
                onNextYear={() => setYear(year + 1)}
            />
        <Calendar year={year} onPickDate={onDatePicked} customClasses={dateMoods} />
        <br />
    <div className="legend">
    <h3>Moods:</h3>
        <span className="box great">Great</span>
        <span className="box good">Good</span>
        <span className="box ok">Ok</span>
        <span className="box bad">Bad</span>
        <span className="box terrible">Terrible</span>
    </div>
    </>
}