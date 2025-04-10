# Catch-a-ride
Catch A Ride is the premier application for catching your next ride. Whether you need to sell, buy, lease, or even just rent a car, Catch A Ride is here to meet all your vehicular supply needs.
Catch A Ride not only opens up multiple avenues to obtain their next vehicle but also offloads their inventory as well. Users can create an account and then browse from a wide selection of vehicles. Here, a user may see listings in their local area and then when the options allow; rent, buy, or lease a vehicle.
Furthermore, users will have the opportunity to create their own listing, should they want to sell, lease, or rent a vehicle to another customer.
Our service offers a fast and efficient portal for any user to circulate vehicles with minimal effort.


### Diagrams Maven: 

```
       http
        ├───user.http
        |───booking.http
        ├───vehicle.http
        |───cost.http
       sql
        ├───catch-ride-prod.sql
        |───catch-ride-test.sql
       src
        ├───main
	│   |───java
	│   │   └───learn
	│   │       └───catch
	│   │           │   App.java
	│   │           │
	│   │           |───controllers
	│   │           │
	│   │           |───data
	│   │           │       DataException.java
	│   │           │       UsertJdbcTemplateRepository.java
	│   │           │       UserRepository.java
	│   │           │       VehicletJdbcTemplateRepository.java
	│   │           │       VehicleRepository.java
	│   │           │       BookingtJdbcTemplateRepository.java
	│   │           │       BookingRepository.java
	│   │           │       CosttJdbcTemplateRepository.java
	│   │           │       CostRepository.java
	│   │           │
	│   │           |───domain
	│   │           │       UserService.java
	│   │           │       VehicleService.java
	│   │           │       BookingService.java
	│   │           │       Cost.java
	│   │           │       Response.java
	│   │           │       Result.java
	│   │           │
	│   │           |───models
	│   │           │       User.java
	│   │           │       Vehicle.java
	│   │           │       Booking.java
	│   │           │       Cost.java   
	│   │           │
	│   │           └───ui
	│   │                   ConsoleIO.java
	│   │                   Controller.java
	│   │                   GenerateRequest.java
	│   │                   MainMenuOption.java
	│   │                   View.java
	│   │
	│   └───resources- > application.properties
	└───test
	    └───java
	        └───learn
	            └───catch
	                ├───data
	                │       UserFileRepositoryTest.java
	                │       UserRepositoryDouble.java
	                │       VehicleFileRepositoryTest.java
	                │       VehicleRepositoryDouble.java
                        │       BookingFileRepositoryTest.java
	                │       BookingRepositoryDouble.java
	                │       CostFileRepositoryTest.java
	                │       CostRepositoryDouble.java
	                │
	                └───domain
	                        UserServiceTest.java
	                        VehicleServiceTest.java
                                BookingServiceTest.java
                                CostServiceTest.java

```


#### database schema

#### Class details & design

#### layer

#### flow

### Wireframes: roughly sketch your UI and how one view transitions to another. You can also use design tools to create wireframes
