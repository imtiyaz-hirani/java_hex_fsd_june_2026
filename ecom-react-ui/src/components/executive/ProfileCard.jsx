import { useContext } from "react"
import { UserContext } from "../MyContext"
  
function ProfileCard() { // Far away component 

    const { name,email } = useContext(UserContext)
    return (
        <div>
            <h1>ProfileCard</h1>
             
                {name } <br />
                {email} 
             

        </div>

    )
}

export default ProfileCard