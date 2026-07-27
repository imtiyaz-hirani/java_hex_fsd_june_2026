import { useState } from "react"
import { user_data } from "../data/users"

function Users() {
     const [users, setUsers] = useState([...user_data])
     
     const filterUsers = (searchStr)=>{
        const regex = new RegExp(searchStr.replace(/%/g, '.*').replace(/_/g, '.'), 'i'); 
        setUsers([...user_data.filter(u=> u.company.name.match(regex ))])
    }
    return (
        <div className="container">
            <div className="row">
                <div className="col-sm-4 mt-4 mb-4">
                    Search by Name: 
                    <input className="form-control" placeholder="Enter the company name to search" 
                    onChange={($event)=> {
                        filterUsers($event.target.value)
                    }} />
                </div>

            </div>
            <div className="row">
                {
                    users.map((user, index) => (
                        <div className="col-md-6 mt-4" key={index}>
                             <div className="card">
                                <div className="card-body">
                                    <div className="card-title"></div>
                                    <p>{user.name} -- {user.email}</p>
                                    <p>Address: 
                                        <br />
                                        {user.address.city} 
                                    </p>
                                    <p>
                                        Company Name:
                                            <br />
                                            {user.company.name}
                                    </p>
                                        
                                 </div>   
                             </div>      
                        </div>
                    ))
                }

            </div>
        </div>
    )
}

export default Users