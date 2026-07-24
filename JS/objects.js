// JSON : JavaScript Object Notation 

const p1 = {
    id : 1,
    title : 'Apple Iphone 6',
    price : 54
}

console.log(p1.id) // Access the prop individually
p1.price = 35000 // Updating the price is allowed
console.log(p1) 

// Destructure this object 
let {id,title,price} = p1
console.log(id)
console.log(title)
console.log(price)

