import './App.css';
import Vehicle from "./Vehicle";

function Home(){
    return(
        <div>
        <section className="jarallax min-vh-100 py-lg-3 py-xl-4 py-xxl-5" datajarallax="true" data-speed="0.65">
           <div className="jarallax-img" style={{backgroundImage: 'url(./home.jpeg)'}}></div>
             <div className="container-fluid position-relative zindex-2 py-5 my-md-3 my-lg-5 text-light">
               <div className="row pb-3 pt-4 pt-sm-5">
                 <div className="col-md-9 col-lg-7 col-xl-6 col-xxl-5 offset-lg-1 pt-5">
                   <div className="card home border-0 rounded py-2 py-sm-3 py-md-4" style={{backgroundColor:'rgba(3, 107, 114, 0.8)'}}>
                      <div className="card-body text-light">
                        <div className="mx-auto pt-2" style={{maxWidth: '535px'}}>
                <h1 className="text-center mb-4 display-5">Welcome to <br/> Catch A Ride application. </h1>
                <p className="text-left fs-3"> 
                Catch A Ride is the premier application for catching your next ride. Whether you need to lease or rent a car, 
                Catch A Ride is here to meet all your vehicular supply needs. 
                Catch A Ride opens up multiple avenues to obtain your next vehicle and offloads your inventory as well.
                Users can create an account and then browse from a wide selection of vehicles. 
                Here, a user may see listings in their local area and then, when the options allow, rent or lease a vehicle. 
                Our service offers a fast and efficient portal for any user to obtain vehicles with minimal effort.
                </p>
                   </div>
                </div>
               </div>
             </div>
            </div>
         </div>
        </section>
        <header className="text-center p-4 text-info">
            <h1 className="text-dark p-4 m-2">Find cars for lease or rent in your area.</h1>
        </header>
       <div className="container">
            <section>
               <Vehicle showAvailableOnly={false} />
            </section>
       </div>
    </div>
    )
}

export default Home;
