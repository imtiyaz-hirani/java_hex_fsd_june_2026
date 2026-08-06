import { useState } from "react"
import { Link } from "react-router"

 function ProductList({data,username,onDelete}){ // Child
     
    return(
        <div> 
            <h3>Welcome {username}</h3> 
             <h1>ProductList</h1>
             {
                data.map((p,index)=>(
                    <div key={index}>
                        {p.id}. &nbsp;&nbsp;
                        {p.name} &nbsp;&nbsp; 
                        <Link onClick={()=> onDelete(p.id)}>
                        <i className="bi bi-trash"></i>
                        </Link>
                    </div>
                ))
             }
        </div>
    )
}

export default ProductList