import { Link } from "react-router"

function Navbar(){

    return(
        <div className="row">
                    <div className="col-lg-12">
                        <nav className="navbar navbar-expand-lg bg-body-tertiary">
                            <div className="container-fluid">
                                <a className="navbar-brand" href="#">Navbar</a>

                                <div className="collapse navbar-collapse" id="navbarSupportedContent">
                                    <ul className="navbar-nav me-auto mb-2 mb-lg-0">
                                        <li className="nav-item">
                                            <Link to="" className="nav-link active" aria-current="page">Home</Link>
                                        </li>
                                        <li className="nav-item">
                                            <Link to='users' className="nav-link"  >Users</Link>
                                        </li>
                                        <li className="nav-item">
                                            <Link to='add-customer' className="nav-link"  >Add Customer</Link>
                                        </li>
                                         <li className="nav-item">
                                            <Link to='todo-list' className="nav-link"  >Todos</Link>
                                        </li>
                                         <li className="nav-item">
                                            <Link to='passengers' className="nav-link"  >Passengers</Link>
                                        </li>
                                        <li className="nav-item dropdown">
                                            <a className="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                                Dropdown
                                            </a>
                                            <ul className="dropdown-menu">
                                                <li><a className="dropdown-item" href="#">Action</a></li>
                                                <li><a className="dropdown-item" href="#">Another action</a></li>
                                                <li><hr className="dropdown-divider" /></li>
                                                <li><a className="dropdown-item" href="#">Something else here</a></li>
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