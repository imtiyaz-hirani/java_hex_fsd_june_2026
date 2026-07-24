const products = 
        [
            {id: 1, title: 'Apple Iphone', price: 34555, stock: 1, category: 'mobiles'},
            {id: 4, title: 'Oppo phone', price: 25999, stock: 2, category: 'mobiles'},
            {id: 3, title: 'OnePlus phone', price: 23555, stock: 2, category: 'mobiles'},
            {id: 2, title: 'Vivo phone', price: 31555, stock: 1, category: 'mobiles'},
            {id: 6, title: 'Dell Laptop', price: 61555, stock: 1, category: 'laptop'}
        ]

// Display the products based on category
const filterProduct = (icategory) => products.filter(p => p.category === icategory)

console.log(filterProduct('mobiles'))
console.log(filterProduct('laptop'))

console.log("=======================Sorting===========================")
function sortProducts(direction){ //'ASC' / 'DESC'
    switch(direction){
        case 'ASC' : 
            return [...products].sort((p1,p2)=>p1.price - p2.price)
            break;
        case 'DESC':
            return [...products].sort((p1,p2)=>p2.price - p1.price)
            break;
        default: 
            return products
    }
}

const sortProductsArrow = (direction)=> 
    direction === 'ASC'? [...products].sort((p1,p2)=>p1.price - p2.price) : 
    direction === 'DESC'? [...products].sort((p1,p2)=>p2.price - p1.price) :
    products

 console.log(sortProducts('ASC'))   
 console.log(sortProductsArrow('DESC'))   

 // console.log(products)

 