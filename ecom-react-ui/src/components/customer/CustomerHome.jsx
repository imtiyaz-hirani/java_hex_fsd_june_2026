import { Outlet, useNavigate } from "react-router"
import Navbar from "./Navbar"
import { useEffect } from "react"

function CustomerHome(){

    return(
        <div> 
            <Navbar />
            <Outlet />
        </div>
    )
}

export default CustomerHome