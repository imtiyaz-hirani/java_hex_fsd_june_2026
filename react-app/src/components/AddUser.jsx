import { useState } from "react"

function AddUser() {

    const [name, setName] = useState('')
    const [email, setEmail] = useState('')
    const [city, setCity] = useState('')
    const [customers, setCustomers] = useState([])
    const [temp, setTemp] = useState([])
    const add = ($event) => {
        $event.preventDefault() // It tells web browser to not refresh the page on form submit
        // create an object of customer 
        let customer = {
            'name': name,
            'email': email,
            'city': city
        }
         
        //push the object in the array
        // customers.push(customer)
        // setCustomers([...customers])
        temp.push(customer)
        setCustomers([...temp])
    }

    const onDelete = (cemail)=>{
        // To delete, filter the array
        setCustomers([...customers.filter((c)=> c.email !== cemail)])
    }
    return (
        <div>
            <div className="container mt-4">
                <div className="row">
                    <div className="col-md-6">
                        <div className="card">
                            <div className="card-body">
                                <form onSubmit={($event) => add($event)}>
                                    <div className="row">
                                        <div className="col-sm-4" style={{ 'textAlign': 'right' }}>
                                            <label>Enter Name: </label>
                                        </div>
                                        <div className="col-md-8">
                                            <input type="text" className="form-control" required
                                                onChange={($event) => setName($event.target.value)} />
                                        </div>
                                    </div>
                                    <div className="row mt-4">
                                        <div className="col-sm-4" style={{ 'textAlign': 'right' }}>
                                            <label>Enter Email: </label>
                                        </div>
                                        <div className="col-md-8">
                                            <input type="email" className="form-control" required
                                                onChange={($event) => setEmail($event.target.value)} />
                                        </div>
                                    </div>
                                    <div className="row mt-4">
                                        <div className="col-sm-4" style={{ 'textAlign': 'right' }}>
                                            <label>Select City: </label>
                                        </div>
                                        <div className="col-md-8">
                                            <select onChange={($event) => setCity($event.target.value)}
                                                className="form-control" required>
                                                <option>--select city--</option>
                                                <option>Mumbai</option>
                                                <option>Chennai</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div className="row mt-4">
                                        <div className="col-sm-4" style={{ 'textAlign': 'right' }}>
                                            <input type="submit" className="btn btn-primary" />
                                        </div>

                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div className="col-md-6" style={{ 'textAlign': 'center' }}>
                        <h3>All Customers </h3>
                        <table className="table">
                            <thead>
                                <tr>
                                    <th scope="col">#</th>
                                    <th scope="col">Name</th>
                                    <th scope="col">Email</th>
                                    <th scope="col">City</th>
                                    <th scope="col">Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    customers.map((c,index) => (
                                        <tr key={index}>
                                            <th scope="row">{index+1}</th>
                                            <td>{c.name}</td>
                                            <td>{c.email}</td>
                                            <td>{c.city}</td>
                                            <td><i className="bi bi-trash" onClick={()=>onDelete(c.email)}></i></td>
                                        </tr>
                                    ))
                                }


                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddUser