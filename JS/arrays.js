const nums = [1,2,3,4,5]

nums.forEach(element => console.log(element))

const marks =[33,66,24,87,49]
const grade =[]
marks.forEach(mark => {
    if(mark > 60) grade.push('A') 
    if(mark > 40 && mark <60)grade.push('B')
    if(mark < 40 ) grade.push('C')
})
console.log(grade)

// Sort the marks in ASC order 
let sortedMArksAsc =[...marks].sort((m1,m2)=> m1-m2) 
// Notice the spread(...) operator - it makes a clone of original array
// so ur marks array stays intact

// Sort the marks in DESC order 
let sortedMArksDesc = [...marks].sort((m1,m2)=> m2-m1)

console.log(sortedMArksAsc)
console.log(sortedMArksDesc)