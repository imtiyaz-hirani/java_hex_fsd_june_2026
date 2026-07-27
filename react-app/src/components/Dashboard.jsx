import { products } from "../data/products"
function Dashboard() {

    return (
        <div>
            <div className="container-fluid">
                
                <div className="row ">
                    {
                        products.map((p) => (
                            <div className="col-sm-3 mt-4">
                                <div className="card">
                                    <img src={p.image} className="card-img-top" alt="Product" />
                                    <div className="card-body">
                                        <h5 className="card-title">{p.name}</h5>
                                        <p className="card-text">Category: {p.category}</p>
                                        <p className="card-text">Price: {p.price}</p>
                                        <p className="card-text">Rating: {p.rating}</p>
                                        
                                        <a href="#" className="btn btn-primary">Add to Cart</a>
                                    </div>
                                </div>
                            </div>
                        ))
                    }


                </div>

            </div>

        </div>
    )
}

export default Dashboard