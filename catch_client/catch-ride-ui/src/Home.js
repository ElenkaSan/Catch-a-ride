import Vehicle from "./Vehicle";

function Home(){
    return(
        <div>
        <header className="text-center p-4 text-info">
            <h1>Welcome to Catch A Ride application.</h1>
            <h2>Find cars for lease or rent in your area.</h2>
        </header>
       <div className="container">
            <section className="m-2 p-3">
                <p className="text-center fw-medium"> 
                Catch A Ride is the premier application for catching your next ride. Whether you need to lease or rent a car, 
                Catch A Ride is here to meet all your vehicular supply needs. 
                Catch A Ride opens up multiple avenues to obtain your next vehicle and offloads your inventory as well.
                Users can create an account and then browse from a wide selection of vehicles. 
                Here, a user may see listings in their local area and then, when the options allow, rent or lease a vehicle. 
                Our service offers a fast and efficient portal for any user to obtain vehicles with minimal effort.
                </p>
            </section>
            <section>
               <Vehicle showAvailableOnly={false} />
            </section>
       </div>
    </div>
    )
}

export default Home;