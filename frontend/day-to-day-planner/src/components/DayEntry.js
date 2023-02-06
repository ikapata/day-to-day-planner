import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import CustomCalendar from "./Calendar";
import DiaryEntry from "./DiaryEntry";
import MoodTracker from "./MoodTracker";
import ToDoList from "./ToDoList";

export default function DayEntry() {
    let { date } = useParams();

    const [dateFromCalendar, setDateFromCalendar] = useState(new Date(date));
    const [mood, setMood] = useState("OK");
    const [toDoList, setToDoList] = useState([]);
    const [diaryEntry, setDiaryEntry] = useState("");

    useEffect(() => {
        const headers = new Headers();
        headers.append(
            "Authorization",
            "Bearer " + localStorage.getItem("userToken")
        );
        headers.append("Content-Type", "application/json");
        fetch("http://http://localhost:8080/api/entries/" + date, {
            headers: headers,
            method: "GET",
            mode: "cors",
            cache: "no-cache",
            credentials: "same-origin",
            referrerPolicy: "no-referrer",

        }).then(data => data.json())
        .then(data => console.log(data))
        .catch(err => console.error(err));

    }, [date])


    const saveForDate = () => {
        const headers = new Headers();
        headers.append("Authorization", localStorage.getItem("userToken"));
        headers.append("Content-Type", "application/json");
        fetch("http://localhost:8080/api/entries", {
            method: "POST",
            headers: headers,
            body: {
                "date": date,
                "diaryEntry": diaryEntry,
                "mood": mood,
                "toDoList": toDoList
            }
        }).then(data => data.json())
            .then(data => console.log(data))
            .catch(err => console.error(err));
    }

    return <div className="container">
        <button className="save-btn" onClick={saveForDate} >Save</button>
        <CustomCalendar date={dateFromCalendar} setDate={setDateFromCalendar} />
        <MoodTracker mood={mood} setMood={setMood} />
        <ToDoList todoList={toDoList} setToDoList={setToDoList} />
        <DiaryEntry entry={diaryEntry} setEntry={setDiaryEntry} />
    </div>
}