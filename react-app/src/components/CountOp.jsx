import { useState } from "react"; //<-- this is a React hook 

function CountOp(){

    let count = 0; // Normal -- React won't track it. on update, it wont show latest value
    const [likeCount, setLikeCount] = useState(0) // <-- Now react tracks this, on update, it will show the latest value

    const incrCount = ()=>{
        setLikeCount(likeCount + 1)
    }

    const decrCount = ()=>{
        if(likeCount === 0)
            return

        setLikeCount(likeCount - 1)
    }
    return(
        <div>
            <h1>My Count Component</h1>
            <button onClick={()=>incrCount()}>Like </button>
             <button onClick={()=>decrCount()}>Dis-Like</button>
             {likeCount}
        </div>
    )
}

export default CountOp