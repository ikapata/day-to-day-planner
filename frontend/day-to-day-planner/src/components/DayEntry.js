import React from "react";
import CustomCalendar from "./Calendar";
import DiaryEntry from "./DiaryEntry";
import MoodTracker from "./MoodTracker";
import ToDoList from "./ToDoList";

export default function DayEntry() {
    return <div className="container">
        <CustomCalendar />
        <MoodTracker />
        <ToDoList />
        <DiaryEntry />
    </div>
}