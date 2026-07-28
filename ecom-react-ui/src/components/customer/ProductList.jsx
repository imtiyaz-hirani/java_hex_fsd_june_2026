import axios from "axios"
import { useEffect, useState } from "react"
import { useParams } from "react-router"

function ProductList() {

    const { categoryId } = useParams()
    const productApi = 'http://localhost:8080/api/product/by-category/'
    const [products, setProducts] = useState([])

    useEffect(() => {
        
        const getAllProductsByCategory = async () => {
            try {
                const response = await axios.get(productApi + categoryId)
                setProducts(response.data)
            }
            catch (err) {

            }
        }

        getAllProductsByCategory()
    }, [categoryId])
    return (

        <div className="container-fluid">

            <div className="row ">
                {
                    products.map((p) => (
                        <div className="col-sm-3 mt-4">
                            <div className="card">
                                <img src="https://placehold.co/600x400" className="card-img-top" alt="Product" />
                                <div className="card-body">
                                    <h5 className="card-title">{p.title}</h5>
                                    <p className="card-text">Price: {p.price}
                                        <br />
                                        Seller Name: {p.sellerName}
                                    </p>
                                    <button className="btn btn-primary">Add to Cart</button>
                                </div>
                            </div>
                        </div>
                    ))
                }


            </div>

        </div>

    )
}

export default ProductList