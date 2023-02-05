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
        // will fetch from backend

        // set the state

    }, [])

    return <div className="container">
        <CustomCalendar date={dateFromCalendar} setDate={setDateFromCalendar} />
        <MoodTracker mood={mood} setMood={setMood} />
        <ToDoList todoList={toDoList} setToDoList={setToDoList} />
        <DiaryEntry entry={diaryEntry} setEntry={setDiaryEntry} />
    </div>
}