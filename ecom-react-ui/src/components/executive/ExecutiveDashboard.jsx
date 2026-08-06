import { useEffect, useState } from "react"
import ProductList from "./ProductList"

 function ExecutiveDashboard(){ //Parent 

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
             {/*  Invocation of Child */}
             <ProductList 
                    data={products} 
                    username = {username}
                    onDelete = {handleDelete}
            />
        </div>
       
    )
}

export default ExecutiveDashboard