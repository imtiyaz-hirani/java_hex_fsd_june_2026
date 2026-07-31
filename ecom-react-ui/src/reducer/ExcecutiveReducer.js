const initialState={
    list : []
}

 const ExecutiveReducer =(state = initialState, action)=>{

    switch(action.type){
        case "GET_ALL":
            return{
                ...state,
                list : action.payload
            }
             
        case "DELETE_EXECUTIVE":
            break
        case "GET_ONE":
            break
        case "ADD":
            break
        default:
            return state
    }

}
export default ExecutiveReducer