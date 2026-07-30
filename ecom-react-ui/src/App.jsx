import { Route, Routes } from "react-router"
import CustomerHome from "./components/customer/CustomerHome"
import PageNotFound from "./components/PageNotFound"
import FeaturedProductList from "./components/customer/FeaturedProductList"
import ProductList from "./components/customer/ProductList"
import Login from "./components/auth/Login"
import SellerDashboard from "./components/seller/SellerDashboard"
import ExecutiveDashboard from "./components/executive/ExecutiveDashboard"
import AdminDashboard from "./components/admin/AdminDashboard"

 function App() {
  return (
       <Routes> 
          <Route path="" element={<CustomerHome />}  > 
            <Route path="" element={<FeaturedProductList />}/>
            <Route path="/product/:categoryId" element={<ProductList />}/>  
          </Route>
          <Route path="login" element={<Login />}/>
          <Route path="seller" element={<SellerDashboard />}/>
          <Route path="executive" element={<ExecutiveDashboard />}/>
          <Route path="admin" element={<AdminDashboard />} />
          <Route path="*" element={<PageNotFound />} />

       </Routes>
  )
}

export default App
