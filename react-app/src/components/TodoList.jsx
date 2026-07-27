import axios from "axios"
import { useEffect, useState } from "react"

function TodoList() {

    const [todos, setTodos] = useState([])
    useEffect(() => {
        const getAllTodos = async () => { // Fn Definition 
            try {
                // CAll API from here. 
                const response = await axios.get('https://jsonplaceholder.typicode.com/todos')
                console.log(response)
                setTodos(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }

        getAllTodos() //Fn call 
    }, [])
    return (
        <div>
            {
                <div className="container">
                    <div className="row">
                        {
                            todos.map((t,index) => (
                                <div className="col-sm-3 mt-4" key={index}>
                                    <div className="card" style={{ 'backgroundColor' : t.completed? '#d2bad2' : '#FFFEEE'}}>
                                        <div className="card-body">
                                            <div className="card-title">
                                                {t.title}
                                            </div>
                                            <p>UserID: {t.userId}</p>
                                            <p>Completed: {t.completed?'Completed': 'Not Completed'}</p>
                                        </div>

                                    </div>
                                </div>

                            ))
                        }

                    </div>
                </div>
            }
        </div>
    )

}
export default TodoList