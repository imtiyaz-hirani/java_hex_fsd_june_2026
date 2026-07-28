import axios from "axios"
import { useEffect, useState } from "react"
import { Link } from "react-router"

function Navbar() {

    const categoryGetAllApi = 'http://localhost:8080/api/category/all'
    const [categories, setCategories] = useState([])

    useEffect(() => {
        const getAllCategory = async () => {
            try {
                const response = await axios.get(categoryGetAllApi)
                setCategories(response.data)
            }
            catch (err) {
                console.log(err)
            }
        }
        getAllCategory()
    }, [])
    return (
        <div className="row">
            <div className="col-lg-12">
                <nav className="navbar navbar-expand-lg bg-body-tertiary">
                    <div className="container-fluid">
                        <Link className="navbar-brand" to=''>My Shop</Link>

                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                                <li className="nav-item">
                                    <Link to="" className="nav-link active" aria-current="page">Home</Link>
                                </li>
                                <li className="nav-item dropdown">
                                    <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                        Categories
                                    </a>
                                    <ul className="dropdown-menu">
                                        {
                                            categories.map((c,index) => (
                                                <li key={index}>
                                                    <Link className="dropdown-item" to={`/product/${c.id}`} >{c.name}</Link></li>
                                            ))
                                        }


                                    </ul>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link disabled" aria-disabled="true">Disabled</a>
                                </li>
                            </ul>
                            <form className="d-flex" role="search">
                                <input className="form-control me-2" type="search" placeholder="Search" aria-label="Search" />
                                <button className="btn btn-outline-success" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                </nav>
            </div>
        </div>
    )
}

export default Navbar