import { useEffect, useState } from "react"
import { useDispatch, useSelector } from "react-redux"
import { getAll } from "../../actions/ExecutiveActions"

function ExecutiveList() {
    const list_data = useSelector((state) => state.executive.list)
    const [list, setList] = useState([])
    const dispatch = useDispatch()
    useEffect(() => {
         dispatch(getAll()) //call API only once and load the store with data
    }, [])

    useEffect(() => {
        setList(list_data)
    }, [list_data]) //once the API brings the data, list_data changes and sothe state also gets the data as this useEffect gets triggers
    return (
        <div className="container">
            <div className="row">
                {
                    list.map((e, index) => (
                        <div className="col-md-6 mt-4" key={index}>
                            <div className="card">
                                <div className="card-body">
                                    <div className="card-title">
                                        {e.name}
                                    </div>
                                    <p>Job Title: {e.jobTitle}</p>
                                    <p>Username: {e.username}</p>
                                </div>
                            </div>
                        </div>
                    ))
                }
            </div>

        </div>
    )
}

export default ExecutiveList