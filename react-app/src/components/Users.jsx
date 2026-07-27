import { user_data } from "../data/users"

function Users() {

    return (
        <div className="container">
            
            <div className="row">
                {
                    user_data.map((user) => (
                        <div className="col-md-6 mt-4">
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