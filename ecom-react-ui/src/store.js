import { combineReducers, configureStore } from "@reduxjs/toolkit";
import CartReducer from "./reducer/CartReducer";
import ExecutiveReducer from "./reducer/ExcecutiveReducer";

export default configureStore({
  reducer: { 
    cartSlice:  CartReducer,
    executive: ExecutiveReducer
  }  
  
})