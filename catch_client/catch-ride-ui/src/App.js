import './App.css';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Navbar from "./Navbar";
import Home from "./Home";
import Vehicle from "./Vehicle";
import VehicleForm from "./VehicleForm";
import NotFound from "./NotFound";
import Login from './Login';
import './axiosConfig';
import RegisterComponent from './RegisterComponent';


function App() {
  return (
       <Router>
            <Navbar/>
            <Routes>
              <Route path="/" element={<Home/>}/>
              <Route path="/vehicle/add" element={<VehicleForm/>}/>
              <Route path="/vehicle/edit/:id" element={<VehicleForm/>}/>
              <Route path="/vehicles/" element={<Vehicle/>}/>
              <Route path="/login/" element={<Login/>}/>
              <Route path="register/" element={<RegisterComponent/>}/>
              <Route path="*" element={<NotFound/>}/>
            </Routes>
        </Router>
  );
}

export default App;
