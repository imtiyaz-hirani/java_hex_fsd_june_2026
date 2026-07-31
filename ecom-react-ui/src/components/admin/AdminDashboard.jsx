import axios from "axios"
import { useEffect } from "react"
import { useNavigate } from "react-router"
import ExecutiveList from "./ExecutiveList"
import { useDispatch } from "react-redux"
import { getAll } from "../../actions/ExecutiveActions"

function AdminDashboard() {

    const navigate = useNavigate()
    
    useEffect(() => {
         
        const verifyAuth = async () => {
            console.log(' in verify auth')
            const token = localStorage.getItem('token')
            let config = {
                headers: {
                    'Authorization': 'Bearer ' + token
                }
            }
            try {
                const response = await axios.get('http://localhost:8080/api/auth/user-details', config)
                console.log("in verify in admin " + response.data)
                const legalUsername = response.data?.username
                const legalRole = response.data?.role
                console.log(legalUsername + "  " + legalRole)

                const localUsername = localStorage.getItem('username')
                const localRole = localStorage.getItem('role')

                if (!(legalUsername === localUsername && localRole === legalRole && legalRole === 'ADMIN')) {
                    // can block the user as well here.. 
                    localStorage.clear()
                    navigate('/page-not-found')
                }
            }
            catch (err) {
                localStorage.clear()
                navigate("/login")
            }
        }

       
        verifyAuth()
        
    }, [])
    return (
        <div>
            <h1>AdminDashboard</h1>
            <ExecutiveList />
        </div>

    )
}

export default AdminDashboard