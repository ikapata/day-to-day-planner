import React from "react";

export default function DiaryEntry(props) {
    const handleChange = (event) => {
        props.setEntry(event.target.value)
    }
    return (
    <div className="diary">
    <h2>What's on your mind today?</h2>
        <textarea
            cols="30" rows="15" value={props.entry} onChange={handleChange}>
        </textarea>
    </div>)
}