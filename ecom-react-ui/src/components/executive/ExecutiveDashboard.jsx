import {  createContext, useContext, useEffect, useState } from "react"
import ProductList from "./ProductList"
import UserProfile from "./UserProfile"
import ProfileCard from "./ProfileCard"
import { UserContext } from "../MyContext"

  function ExecutiveDashboard(){ //Parent 
    const userObj = {
        name: 'Harry Potter',
        email: 'harry@gmail.com'
    }
    const [products, setProducts] = useState([])
    const [username, setUsername] = useState('')
     
    useEffect(()=>{
        let temp = [
            {id: 1, name: 'Apple Phone'}, 
            {id: 2, name: 'Oppo Phone'}, 
        ]
        setProducts(temp)
        setUsername(localStorage.getItem('username'))
    },[])

    const handleDelete = (pid)=>{
        // call api 
        setProducts([...products.filter(p=> p.id !== pid)])
    }
    return(
        <div>  
             <h1>ExecutiveDashboard</h1>
              <hr />
               {/*  User Profile */}
               <UserProfile />
               <hr />
             {/*  Invocation of Child */}
             <ProductList 
                    data={products} 
                    username = {username}
                    onDelete = {handleDelete}
            />

            <UserContext.Provider value={userObj}>
                <ProfileCard />
            </UserContext.Provider>
        </div>
       
    )
}

export default ExecutiveDashboard