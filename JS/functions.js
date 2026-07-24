function sayHello(fname,lname){
    return fname + " " + lname
}

const sayHelloArrow = (fname,lname) => fname + " " + lname

console.log(sayHello('jack', 'orielly'))
console.log(sayHelloArrow('keith', 'ledger'))

function displayPerson(id,name,age){ //<-- notice how the Fn has multiple lines
    console.log(id)
    console.log(name)
    console.log(age)
}

const displayPersonArrow = (id,name,age) => {  
    console.log(id)
    console.log(name)
    console.log(age)
}
displayPerson(1,'Mark', 34)
displayPersonArrow(2,'Keith', 43)

