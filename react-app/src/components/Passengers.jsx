import axios from "axios"
import { useEffect, useState } from "react"
import { Link } from "react-router"
import '../css/passengers.css'
function Passengers() {

    const [passengers, setPassengers] = useState([])
    const [page,setPage] = useState(0)
    const [size, setSize] = useState(10)

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

    }, [page,size]) // if any of these page and size value changes, useEffect gets called and API gets called.
    return (
        <div className="container">
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
                                <th scope="row"> {page !== 0? (page*size) +index + 1 : (index+1)}</th>
                                <td className="text-style">{p.name}</td>
                                <td>{p.contact}</td>
                                <td>{p.createdAt.split('T')[0]}</td>
                                <td> 
                                    <i class="bi bi-eye fs-1"></i> &nbsp;&nbsp;&nbsp;
                                    <i class="bi bi-trash fs-2"></i>
                                     </td>
                            </tr>
                        ))
                    }


                </tbody>
            </table>
            <div>
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <li class="page-item"><Link class="page-link" 
                        onClick={()=> page > 0 ? setPage(page-1) : ''}
                        >Previous</Link></li>
                        <li class="page-item"> 
                            <select className="form-control" onChange={($event)=> setSize($event.target.value)}>
                                <option>10</option>
                                <option>20</option>
                                <option>50</option>
                                <option>100</option>
                            </select>
                        </li>
                        
                        <li class="page-item"><Link class="page-link"  
                        onClick={()=> setPage(page+1)}>
                            Next</Link></li>
                    </ul>
                </nav>
            </div>
        </div>

    )
}

export default Passengers