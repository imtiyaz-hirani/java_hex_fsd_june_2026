import axios from "axios"
import { useEffect, useState } from "react"

function SellerProductList() {

    const [products, setProducts] = useState([])
    const [img, setImg] = useState('')
    const [msg, setMsg] = useState('')
    const [count, setCount] = useState(0)
    const [progress, setProgress] = useState(0)

    useEffect(() => {
        const getAllProducts = async () => {
            const response = await axios.get('http://localhost:8080/api/product/by-seller',
                {
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('token')
                    }
                }
            )
            setProducts(response.data)
        }

        getAllProducts()
    }, [count])

    const upload = async (pid) => {
        if (img === '' || !img)
            return

        const formData = new FormData()
        formData.append('pImage', img)
        try {
            const response = await axios.post(`http://localhost:8080/api/product/image/upload/${pid}`,
                formData,
                {
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('token')
                    },
                    onUploadProgress: ($event) => {
                        
                        setProgress(Math.round($event.loaded * 100 / $event.total))
                    }
                }
            )
            setCount(count + 1)
            setMsg(response?.data?.message)

        }
        catch (err) {
            setMsg(err.response?.data?.message)
        }

    }
    return (
        <div>
            <h1>ProductList</h1>
            {
                progress === 0 ? "" : <div className="alert alert-primary">
                {
                    progress + '%'
                }
            </div>
            }
            
            {
                products.map((p, index) => (
                    <div key={index}>

                        {p.imageUrl !== null ?
                            <img src={`images/${p.imageUrl.split('images\\')[1]}`} style={{ 'width': '75px' }} /> : ""
                        }
                        <br />
                        {p.title} <br />
                        INR. {p.price} <br />

                        <input type="file" onChange={($event) => setImg($event.target.files[0])} />
                        <br /> &nbsp;&nbsp;&nbsp;

                        <button className="btn btn-secondary" onClick={() => upload(p.id)}> Upload Image </button>
                        <hr />
                    </div>

                ))
            }
        </div>
    )
}

export default SellerProductList