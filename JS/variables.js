// 3 ways to create variable in JS 
const name ='john doe' //<-- Note that we do not give data types 
let fname = 'john' //<-- Note that semi colon is optional
var lname = 'doe'

/**
 * const is similar to final variable in java 
 * let can let u change the value but it is mostly used in methods
 * var takes everything but its deprecated. 
 */
// name = 'Jane doe' //problem - name was defined as const
fname='Jane'
lname='DOE'

console.log(name)
console.log(fname)
console.log(lname)