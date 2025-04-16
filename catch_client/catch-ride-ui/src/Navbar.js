import {Link} from "react-router-dom";

function Navbar(){
    return(
        <div>
            <nav className="navbar navbar-expand-xl navbar-dark bg-dark">
                {/* <div className="container"> */}
                    <div className="nav-link">
                        <Link className="p-5 text-light nav-item" to={'/'}>Home</Link>
                        <Link className="p-5 text-light nav-item" to={'/vehicles'}>See All Cars</Link>
                        <Link className="p-5 text-light nav-item" to={'/vehicle/add'}>Add Car</Link>
                    </div>

                {/* </div> */}
            </nav>
        </div>
   )
}


export default Navbar;