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
        let newTodoList = props.todoList.map(item => {
            if (item.name === itemToCheck.name) {
                item.checked = ! item.checked;
                } 
                return item;
        })
        props.setToDoList(
            newTodoList)
            }


    const handleAdd = () => {
        let item = {
            name: currentItem,
            checked: false
        }
        let newToDoList = [...props.todoList, item];

        setCurrentItem("");
        props.setToDoList(
            newToDoList
        )
    }
    console.log(currentItem);
    console.log(props.todoList)
    let toDoListItems = props.todoList.map(item => {
        return <li key={item.name}>
            <input type="checkbox"  checked={item.checked} onChange={(event) => handleCheckChange(event, item)} />
            <span style={{margin: "10px"}}>{item.name}</span>
            <button onClick={() => handleEdit(item.name)}>✎</button>
            <button onClick={() => handleDelete(item.name)}>🗑️</button>
        </li>
    })
    return (
        <div className="todo">
            <h2>Your todo list for today:</h2>
            <input type="text" value={currentItem} onChange={(event) => setCurrentItem(event.target.value)} /><button onClick={handleAdd}>+</button>
            <ul className="todo-list">
                {toDoListItems}
            </ul>
        </div>
    )
}