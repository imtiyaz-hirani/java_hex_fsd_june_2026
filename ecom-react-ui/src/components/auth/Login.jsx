import AuthNavbar from "./AuthNavbar"
import axios from "axios"
import { useState } from "react"
import { Link, useNavigate } from "react-router"

function Login(){
    const [username, setUsername] = useState('')
    const [password, setPassword] = useState('')
    const [errorMsg, setErrorMessage] = useState('')

    const navigate = useNavigate()

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
            // Save the key info like username, role and token in localStorage of User's Web browser 
            localStorage.setItem('username', username)
            localStorage.setItem('token', response.data?.token)
            localStorage.setItem('role', response.data?.role)

            switch(response.data?.role){
                case 'ADMIN':
                    navigate('/admin')
                    break;
                case 'CUSTOMER':
                    navigate('/')
                    break;
                case 'SELLER':
                    navigate('/seller')
                    break;
                case 'EXECUTIVE':
                    navigate('/executive')
                    break;
            }
        }
        catch (err) {
            setErrorMessage('Invalid credentials')
         }
    }
    return(
        <>
            <AuthNavbar />
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
 

export default Login