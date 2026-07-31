const initialState ={
    cart : []  //p1
}

 const CartReducer = (state=initialState , action)=>{

    switch(action.type){
        case 'ADD_TO_CART':
            return{
               ...state, //new state cloned from previous one 
               cart : [...state.cart, action.payload] 
            }
             
        case  'DELETE_TO_CART':
            return{
                ...state, //new state cloned from previous one 
                cart : state.cart.filter(p=>p.id !== action.payload)
                // all those products having id not equal to given id(for delete) can stay 
            }
            
        default:
            return state
            
    }
}
export default CartReducer
/**
 action = { 
      type: 'ADD_TO_CART',
      payload : product  
}

action = { 
    type: 'DELETE_TO_CART',
      payload : pid  
}

 numbers = [1,2,3]
 add 4 to numbers 
 numbers = [4] --- wrong 
 numbers = [...numbers , 4] --- [1,2,3,4]

 */