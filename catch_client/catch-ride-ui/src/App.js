
import './App.css';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Navbar from "./Navbar";
import Home from "./Home";
import Vehicle from "./Vehicle";
import VehicleForm from "./VehicleForm";
import NotFound from "./NotFound";


function App() {
  return (
       <Router>
            <Navbar/>
            <Routes>
              <Route path="/" element={<Home/>}/>
              <Route path="/vehicle/add" element={<Vehicle/>}/>
              <Route path="/vehicle/edit/:id" element={<VehicleForm/>}/>
              <Route path="*" element={<NotFound/>}/>
            </Routes>
        </Router>
  );
}

export default App;
