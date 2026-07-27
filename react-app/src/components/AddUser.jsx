import { useState } from "react"

function AddUser() {

    const [name,setName] = useState('')
    const [email,setEmail] = useState('')
    const [city,setCity] = useState('')

    const add = ($event)=>{
        $event.preventDefault() // It tells web browser to not refresh the page on form submit
        console.log(name)
        console.log(email)
        console.log(city)
    }
    return (
        <div>
            <div className="container mt-4">
                <div className="row">
                    <div className="col-md-6">
                        <div className="card">
                            <div className="card-body">
                                <form onSubmit={($event)=> add($event)}>
                                    <div className="row">
                                        <div className="col-sm-4" style={{ 'textAlign': 'right' }}>
                                            <label>Enter Name: </label>
                                        </div>
                                        <div className="col-md-8">
                                            <input type="text" className="form-control" required
                                            onChange={($event)=> setName($event.target.value)} />
                                        </div>
                                    </div>
                                    <div className="row mt-4">
                                        <div className="col-sm-4" style={{ 'textAlign': 'right' }}>
                                            <label>Enter Email: </label>
                                        </div>
                                        <div className="col-md-8">
                                            <input type="email" className="form-control" required 
                                            onChange={($event)=> setEmail($event.target.value)}/>
                                        </div>
                                    </div>
                                    <div className="row mt-4">
                                        <div className="col-sm-4" style={{ 'textAlign': 'right' }}>
                                            <label>Select City: </label>
                                        </div>
                                        <div className="col-md-8">
                                            <select onChange={($event)=> setCity($event.target.value)} 
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
                    <div className="col-md-6">
                        here, do anything
                    </div>
                </div>
            </div>
        </div>
    )
}

export default AddUser