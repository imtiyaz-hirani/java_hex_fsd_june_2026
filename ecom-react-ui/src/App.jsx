import { Route, Routes } from "react-router"
import CustomerHome from "./components/customer/CustomerHome"
import PageNotFound from "./components/PageNotFound"
import FeaturedProductList from "./components/customer/FeaturedProductList"
import ProductList from "./components/customer/ProductList"

 function App() {
  return (
       <Routes> 
          <Route path="" element={<CustomerHome />}  > 
            <Route path="" element={<FeaturedProductList />}/>
            <Route path="/product/:category-id" element={<ProductList />}/>
          </Route>
          
          <Route path="*" element={<PageNotFound />} />

       </Routes>
  )
}

export default App
