import React, {useEffect, useState} from "react";
import {useParams} from "react-router";
import CustomCalendar from "./Calendar";
import DiaryEntry from "./DiaryEntry";
import MoodTracker from "./MoodTracker";
import ToDoList from "./ToDoList";
import {BASE_URI} from "../constants/constants";
import { getFormattedDate } from "../utils/dateUtils";

export default function DayEntry() {
    let {date} = useParams();

    const [dateFromCalendar, setDateFromCalendar] = useState(new Date(date));
    const [mood, setMood] = useState("OK");
    const [toDoList, setToDoList] = useState([]);
    const [diaryEntry, setDiaryEntry] = useState("");
    const [entryId, setEntryId] = useState(null);
    function setData(data) {
        if (data) {
            setEntryId(data.id);
            setMood(data.mood);
            setDiaryEntry(data.diaryEntry);
            setToDoList(data.todoList);
        }}

        useEffect(() => {
            let dateString = getFormattedDate(dateFromCalendar);

            const headers = new Headers();
            headers.append("Content-Type", "application/json");
            headers.append("authorization", "Bearer " + localStorage.getItem("userToken"));
            let requestOptions = {
                method: 'GET',
                headers: headers,
                redirect: 'follow',
                referer: "no-referer"
            };

            fetch(BASE_URI + "/api/entries/" + dateString, requestOptions)
                .then(response => response.json())
                .then(data => {
                    setData(data);
                })
                .catch(error => {
                    setEntryId(null);
                    setToDoList([]);
                    setMood("OK");
                    setDiaryEntry("");
                });

        }, [date])


        const saveForDate = () => {
            const raw = JSON.stringify({
                "id": entryId,
                "date": date,
                "diaryEntry": diaryEntry,
                "mood": mood,
                "todoList": toDoList
            });
            const headers = new Headers();
            headers.append("Authorization", "Bearer " + localStorage.getItem("userToken"));
            headers.append("Content-Type", "application/json");
            fetch(BASE_URI + "/api/entries", {
                method: "POST",
                headers: headers,
                referrer: "no-referrer",
                body: raw,
                redirect: 'follow'
            }).then(data => data.json())
                .then(data => {
                    setData(data);
                })
                .catch(err => console.error(err));
        }

        return <div className="container">
            <button className="save-btn" onClick={saveForDate}>Save</button>
            <CustomCalendar date={dateFromCalendar} setDate={setDateFromCalendar}/>
            <MoodTracker mood={mood} setMood={setMood}/>
            <ToDoList todoList={toDoList} setToDoList={setToDoList}/>
            <DiaryEntry entry={diaryEntry} setEntry={setDiaryEntry}/>
        </div>

}