import axios from "axios"

export const getAll = () => async (dispatch) => {
    const response = await axios.get('http://localhost:8080/api/executive/all',{
        headers : {
            'Authorization' : 'Bearer ' + localStorage.getItem('token')
        }
    }) 
    dispatch ({
        type: "GET_ALL",
        payload: response.data
    } )
}


export const deleteById = (id) => ({
    type: "DELETE_EXECUTIVE",
    payload: ""
})
export const add = (executive) => ({
    type: "GET_ONE",
    payload: ""
})
export const getById = (id) => ({
    type: "ADD",
    payload: ""
})

