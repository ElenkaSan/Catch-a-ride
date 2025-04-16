import {Link} from "react-router-dom";
import DEFAULT from "./logo.png";

function Navbar(){
    return(
        <div>
            <nav className="navbar navbar-expand-xl navbar-dark bg-secondary">
                {/* <div className="container"> */}
                    <div className="nav-link">
                        <Link className="p-3 text-light nav-item" to={'/'}>
                        Catch A Ride <img src={DEFAULT} alt="trip" style={{ height:'30px', width:'30px'}}/>
                        </Link>
                        <Link className="p-5 text-light nav-item" to={'/vehicles'}>Availabale Cars</Link>
                        {/* <Link className="p-5 text-light nav-item" to={'/vehicle/add'}>Add Car</Link> */}
                        <Link className="p-5 text-light nav-item" to={'/booking/add'}>Add Booking</Link>
                        <Link className="p-5 text-light nav-item" to={'/login'}>Login / Signup</Link>
                    </div>
                {/* </div> */}
            </nav>
        </div>
   )
}


export default Navbar;