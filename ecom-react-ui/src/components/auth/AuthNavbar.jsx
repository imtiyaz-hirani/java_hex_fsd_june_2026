import axios from "axios"
import { useState } from "react"
import { Link } from "react-router"

function AuthNavbar() {
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [errorMsg, setErrorMessage] = useState('')

    const onLogin = async ($event) => {
        $event.preventDefault()
        // call login API
        // create the header from username and password 
        let authToken = window.btoa(username + ":" + password)
        let config = {
            headers: {
                'Authorization': 'Basic ' + authToken
            }
        }
        try {
            const response = await axios.get('http://localhost:8080/api/auth/login', config)
            console.log(response.data)
        }
        catch (err) {
            setErrorMessage('Invalid credentials')
         }


    }
    return (
        <>
            <nav className="navbar bg-body-tertiary mb-4">
                <div className="container-fluid">
                    <a className="navbar-brand" href="#">My Shop</a>
                </div>
            </nav>

            <div className="container mt-4">
                <div className="row">
                    <div className="col-sm-4"> </div>
                    <div className="col-sm-4">
                        <div className="card">
                            <div className="card-body">
                                <div className="card-title">
                                    <h3>Login</h3>
                                </div>
                                <form onSubmit={($event) => onLogin($event)}>
                                    {
                                        errorMsg !== ''?<div className="alert alert-danger mt-2">
                                         {errorMsg}
                                    </div> :
                                    ""
                                    }
                                    
                                    <div className="mt-4">
                                        <label>Username: </label>
                                        <input type="text" className="form-control"
                                            onChange={($event) =>{ setUsername($event.target.value)
                                                setErrorMessage('')
                                            }} />
                                    </div>
                                    <div className="mt-4">
                                        <label>Password: </label>
                                        <input type="password" className="form-control"
                                            onChange={($event) => {setPassword($event.target.value)
                                                setErrorMessage('')
                                            }} />
                                    </div>
                                    <div className="mt-4">

                                        <input type="submit" valie="login" className="btn btn-primary" />
                                    </div>
                                    <div className="mt-4">
                                        Don't have an account?  &nbsp;&nbsp;&nbsp;
                                        <Link to='/sign-up' style={{ 'textDecoration': 'none' }}>Sign Up</Link>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </>

    )
}

export default AuthNavbar