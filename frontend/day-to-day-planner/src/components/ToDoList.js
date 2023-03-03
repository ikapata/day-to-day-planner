import React, { useState } from "react";

export default function ToDoList(props) {
    const [currentItem, setCurrentItem] = useState("");

    const handleEdit = (name) => {
        setCurrentItem(name);
        props.setToDoList(
            prev => {
                return prev.filter(item => item.name !== name);
            }
        )
    }

    const handleDelete = (name) => {
        props.setToDoList(
            prev => {
                return prev.filter(item => item.name !== name);
            }
        )
    }

    const handleCheckChange = (event, itemToCheck) => {
        let newToDoList = props.toDoList.map(item => {
            if (item.name === itemToCheck.name) {
                item.checked = ! item.checked;
                } 
                return item;
        })
        props.setToDoList(
            newToDoList)
            }


    const handleAdd = () => {
        let item = {
            name: currentItem,
            checked: false
        }
        let newToDoList = [...props.toDoList, item];

        setCurrentItem("");
        props.setToDoList(
            newToDoList
        )
    }
    let toDoListItems = props.toDoList.map(item => {
        return <li key={item.name} className="to-do-item">
            <input type="checkbox" className="to-do-item-input" checked={item.checked} onChange={(event) => handleCheckChange(event, item)} />
            <label htmlFor={item.name}>
            <span style={{margin: "10px"}}>{item.name}</span>
            <button onClick={() => handleEdit(item.name)}><span role="img" aria-label="edit">✎</span></button>
            <button onClick={() => handleDelete(item.name)}><span role="img" aria-label="delete">🗑️</span></button>
            </label>
        </li>
    })
    return (
        <div className="todo">
            <h2>Your to-do list for today:</h2>
            <input type="text" value={currentItem} onChange={(event) => setCurrentItem(event.target.value)} /><button onClick={handleAdd}>+</button>
            <ul className="todo-list">
                {toDoListItems}
            </ul>
        </div>
    )
}