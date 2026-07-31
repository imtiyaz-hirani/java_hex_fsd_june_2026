 // this product obj will come from component
export const addToCart = (product)=>({ 
      type: 'ADD_TO_CART',
      payload : product  
})   

export const deleteFromCart = (pid) => ({ 
    type: 'DELETE_TO_CART',
      payload : pid  
} )



 