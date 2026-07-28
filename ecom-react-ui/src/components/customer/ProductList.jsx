import axios from "axios"
import { useEffect, useState } from "react"
import { useParams } from "react-router"

function ProductList() {

    const { categoryId } = useParams()
    const productApi = 'http://localhost:8080/api/product/by-category/'
    const [products, setProducts] = useState([])
    const [page, setPage] = useState(0)
    const [size, setSize] = useState(8)

    useEffect(() => {

        const getAllProductsByCategory = async () => {
            try {
                const response = await axios.get(`${productApi}${categoryId}?page=${page}&size=${size}`)
                setProducts(response.data)
            }
            catch (err) {

            }
        }

        getAllProductsByCategory()
    }, [categoryId, page, size])

    const computePage = (op)=>{
        switch(op){
            case 'PREV':
                setPage(page === 0? page : (page-1))
                break
            case 'NEXT':
                setPage(page+1)
                break
        }
    }

    const computeSize = (isize)=>{
        setSize(isize)
    }
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
            <div className="row">
                <div className="col-sm-4"></div>
                <div className="col-sm-4">
                    <nav aria-label="Page navigation example">
                        <div className="alert  "  >
                            <ul className="pagination">
                                <li className="page-item"><button className="page-link" 
                                onClick={()=> computePage('PREV')}
                                >Previous</button></li>
                                <li className="page-item"> 
                                    <select onChange={($event)=> computeSize($event.target.value)} className="form-control">
                                        <option>8</option>
                                        <option>12</option>
                                        <option>16</option>
                                        <option>20</option>
                                    </select>    
                                </li>
                                <li className="page-item"><button className="page-link" 
                                onClick={()=>computePage('NEXT')}
                                >Next</button></li>
                            </ul>
                        </div>

                    </nav>
                </div>
            </div>
        </div>

    )
}

export default ProductList