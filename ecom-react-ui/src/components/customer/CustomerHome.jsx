import { Outlet } from "react-router"
import Navbar from "./Navbar"

function CustomerHome(){

    return(
        <div> 
            <Navbar />
            <Outlet />
        </div>
    )
}

export default CustomerHome