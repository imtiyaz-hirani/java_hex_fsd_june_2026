import { useDispatch, useSelector } from "react-redux"
import { Link } from "react-router"
import { deleteFromCart } from "../../actions/CartAction"

function Cart(){

    const cartItems = useSelector(state=>state.cartSlice.cart)
    const dispatch = useDispatch()

    const onRemove = (pid)=>{
        dispatch(deleteFromCart(pid))
    }
    return(
        <div>
            <h1>Cart</h1>
        {
            cartItems.map((p,index)=>(
                <div key={index}>
                    {p.id} -- {p.title} -- {p.price} -- {p.sellerName} &nbsp;&nbsp;&nbsp; 
                    <i class="bi bi-trash" onClick={()=>onRemove(p.id)}></i>
                    <br />
                </div>
            ))
        }
        </div>
    )
}

export default Cart