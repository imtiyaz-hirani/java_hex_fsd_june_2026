import { Route, Routes } from "react-router"
import AddUser from "./components/AddUser"
import Dashboard from "./components/Dashboard"
import Users from "./components/Users"
import PageNotFound from "./components/PageNotFound"
import Navbar from "./components/Navbar"

function App() { // Component - Base

  return (
    <div>
      <Navbar />
      <Routes>
        <Route path='' element={<Dashboard />} />
        <Route path='users' element={<Users />} />
        <Route path='add-customer' element={<AddUser />} />
        <Route path='*' element={<PageNotFound />} />
      </Routes>
    </div>


  )
}

export default App
