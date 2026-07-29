import axios from "axios"
import { useEffect, useState } from "react"
import { Link } from "react-router"
import '../css/passengers.css'
function Passengers() {

    const [passengers, setPassengers] = useState([])
    const [page, setPage] = useState(0)
    const [size, setSize] = useState(10)

    // States for reading form inout
    const [name, setName] = useState('')
    const [contact, setContact] = useState('')

    const [successMsg, setSuccessMsg] = useState('')
    const [errMsg,setErrorMsg] = useState('') 
    const [nameErrMsg, setNameErrMsg] = useState('')
    const [contactErrMsg, setContactErrMsg] = useState('')
    const [count, setCount] = useState(0)

    useEffect(() => {
        // Fn for calling API 
        const getAllPassengers = async () => {
            try {
                const response = await axios.get(`http://localhost:8080/api/passenger/all?page=${page}&size=${size}`)
                // save API response in state variable using setter 
                setPassengers(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        // Calling Fn
        getAllPassengers()

    }, [page, size, count]) // if any of these page and size value changes, useEffect gets called and API gets called.

    const add = async() => {
        // POST API 
        let body = {
            "name": name,
            "contact": contact
        }
        try{
            await axios.post('http://localhost:8080/api/passenger/add',body)
            setSuccessMsg('Passenger added to system')
            setErrorMsg('')
            setCount(count + 1)
        }
        catch(err){
            console.log(err.response.data)
            setErrorMsg(err.response.data.message)
            setNameErrMsg(err.response.data.name)
            setContactErrMsg(err.response.data.contact)
            setSuccessMsg('')
        }
        
    }

    const onAdd = ()=>{
        // clean up
        setSuccessMsg('')
        setErrorMsg('')
        setNameErrMsg('')
        setContactErrMsg('')
        setName('') 
        setContact('')
    }
    return (
        <div className="container">
            <div className="mt-4">
                <button className="btn btn-secondary " onClick={()=>onAdd() } data-bs-toggle="modal" data-bs-target="#addPassengerForm">
                    +Add Passenger
                </button>
            </div>
            <table className="table mt-4">
                <thead>
                    <tr>
                        <th scope="col">#</th>
                        <th scope="col">Name</th>
                        <th scope="col">Contact</th>
                        <th scope="col">Date Added</th>
                        <th scope="col">Actions</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        passengers.map((p, index) => (
                            <tr key={index}>
                                <th scope="row"> {page !== 0 ? (page * size) + index + 1 : (index + 1)}</th>
                                <td className="text-style">{p.name}</td>
                                <td>{p.contact}</td>
                                <td>{p.createdAt.split('T')[0]}</td>
                                <td>
                                    <i className="bi bi-eye fs-1"></i> &nbsp;&nbsp;&nbsp;
                                    <i className="bi bi-trash fs-2"></i>
                                </td>
                            </tr>
                        ))
                    }


                </tbody>
            </table>
            <div>
                <nav aria-label="Page navigation example">
                    <ul className="pagination">
                        <li className="page-item"><Link className="page-link"
                            onClick={() => page > 0 ? setPage(page - 1) : ''}
                        >Previous</Link></li>
                        <li className="page-item">
                            <select className="form-control" onChange={($event) => setSize($event.target.value)}>
                                <option>10</option>
                                <option>20</option>
                                <option>50</option>
                                <option>100</option>
                            </select>
                        </li>

                        <li className="page-item"><Link className="page-link"
                            onClick={() => setPage(page + 1)}>
                            Next</Link></li>
                    </ul>
                </nav>
            </div>


            {/* <!-- Modal --> */}
            <div className="modal fade" id="addPassengerForm" aria-hidden="true">
                <div className="modal-dialog modal-dialog-centered">
                    <div className="modal-content">
                        <div className="modal-header">
                            <h1 className="modal-title fs-5" id="exampleModalLabel">Add Passenger</h1>
                            <button type="button" className="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div className="modal-body">
                            {
                                successMsg !== ''? <div className="alert alert-primary mb-4">
                                {successMsg}
                            </div> : ""
                            }

                            {
                                errMsg !== ''? <div className="alert alert-danger mb-4">
                                {errMsg}
                            </div> : ""
                            }
                            
                            <div className=" mb-4">
                                <label>Name: </label> <span>{nameErrMsg}</span>
                                <input type="text" className="form-control" value={name}
                                    onChange={($event) => {
                                        setName($event.target.value)
                                        setNameErrMsg('')    
                                    }} />
                            </div>
                            <div className="mb-4">
                                <label>Contact: &nbsp;&nbsp;&nbsp;</label><span style={{color: 'red', fontSize: '14px'}}>{contactErrMsg}</span>
                                <input type="number" className="form-control" value={contact}
                                    onChange={($event) => {
                                        setContact($event.target.value)
                                        setContactErrMsg('')
                                        }} />
                            </div>
                        </div>
                        <div className="modal-footer">
                            <button type="button" className="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="button" className="btn btn-primary" onClick={() => add()}>Save changes</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    )
}

export default Passengers