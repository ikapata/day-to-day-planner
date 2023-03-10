import React from "react";

export default function MoodTracker(props) {


    const handleSelect = (event) => {
        props.setMood(event.target.value);
    }

    const moods = {
        "GREAT":"😄",
         "GOOD":"😊", 
         "OK":"😐", 
         "BAD":"🙁", 
         "TERRIBLE":"😓"
    }


    const moodsInput = Object.keys(moods).map(mood => {
        return <span key={mood} >
            <input type="radio" name="mood" id={mood} value={mood} checked={mood === props.mood} onChange={handleSelect} /><label htmlFor={mood}>{moods[mood]}</label>
        </span>
    })
    return <div className="mood">
        <h2>What's your mood today?</h2>
        <div className="moods">
            {moodsInput}
        </div>
    </div>
}