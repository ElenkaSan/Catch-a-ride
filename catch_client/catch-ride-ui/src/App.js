import './App.css';
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Navbar from "./Navbar";
import Home from "./Home";
import Booking from "./Booking";
import BookingForm from "./BookingForm";
import VehicleList from "./VehicleList";
import VehicleForm from "./VehicleForm";
import NotFound from "./NotFound";
import Login from './Login';
import './axiosConfig';
import RegisterComponent from './RegisterComponent';
import AdminPage from './AdminPage'; //just for now then late will do if user or admin will be logged in
import UserInfoForm from './UserInfoForm';
import UserPage from "./UserPage";

function App() {
  return (
       <Router>
            <Navbar/>
            <Routes>
              <Route path="/" element={<Home/>}/>
              <Route path="/booking" element={<Booking/>}/>
              <Route path="/booking/add" element={<BookingForm/>}/>
              <Route path="/booking/edit/:bookingId" element={<BookingForm/>}/>
              <Route path="/vehicle/add" element={<VehicleForm/>}/>
              <Route path="/vehicle/edit/:id" element={<VehicleForm/>}/>
              <Route path="/vehicles/" element={<VehicleList/>}/>
              <Route path="/login/" element={<Login/>}/>
              <Route path="register/" element={<RegisterComponent/>}/>
              <Route path="/admin" element={<AdminPage />} />
              <Route path="/user/edit/:userId" element={<UserInfoForm />} />
              <Route path="/user/add" element={<UserInfoForm />} />
              <Route path="/user" element={<UserPage />} />
              <Route path="*" element={<NotFound/>}/>
            </Routes>
        </Router>
  );
}

export default App;
