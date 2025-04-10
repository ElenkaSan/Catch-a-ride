# Catch-a-ride
Catch A Ride is the premier application for catching your next ride. Whether you need to sell, buy, lease, or even just rent a car, Catch A Ride is here to meet all your vehicular supply needs.
Catch A Ride not only opens up multiple avenues to obtain their next vehicle but also offloads their inventory as well. Users can create an account and then browse from a wide selection of vehicles. Here, a user may see listings in their local area and then when the options allow; rent, buy, or lease a vehicle.
Furthermore, users will have the opportunity to create their own listing, should they want to sell, lease, or rent a vehicle to another customer.
Our service offers a fast and efficient portal for any user to circulate vehicles with minimal effort.

### Work Plan
#### **Day 1 - Back End Development**
1. Add Spring DI (1 Hour) - Dev 1
2. Create Models (1 Hour) - Dev 2
3. Build Database (1 Hour) - Dev 3
4. Build Repositories (2 Hours) - Dev 1
5. Add Repository Unit Tests (2 Hours) - Dev 2
6. Build Services (2 Hours) - Dev 3
7. Add Service Unit Tests (2 Hours) - Dev 1
8. Error Handling (2 Hours) - Dev 2
9. Add Controllers and Mappers (2 Hours) - Dev 3
10. Debugging (3 Hours) - All Devs

#### **Day 2 - ?**
   
### Diagrams Maven: 
#### Class details & design

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
	│   │           │       ErrorResponse.java
	│   │           │       GlobalExceptionHandler.java
	│   │           │       UserController.java
	│   │           │       VehicleController.java
	│   │           │       BookingController.java
	│   │           │       CostController.java
	│   │           │
	│   │           |───data
	│   │           │       DataException.java
	│   │           │       UsertJdbcTemplateRepository.java
	│   │           │       UserRepository.java
	│   │           │       VehicleJdbcTemplateRepository.java
	│   │           │       VehicleRepository.java
	│   │           │       BookingtJdbcTemplateRepository.java
	│   │           │       BookingRepository.java
	│   │           │       CostJdbcTemplateRepository.java
	│   │           │       CostRepository.java
	│   │           │
	│   │           |───domain
	│   │           │       UserService.java
	│   │           │       VehicleService.java
	│   │           │       BookingService.java
	│   │           │       Cost.java
	│   │           │       Response.java
	│   │           │       Result.java
        │   │           │       ResultType.java
	│   │           │
	│   │           └───models
	│   │                   User.java
	│   │                   Vehicle.java
	│   │                   Booking.java
	│   │                   Cost.java   
	│   │           
	│   │
	│   └───resources- > application.properties
	└───test
	    └───java
	        └───learn
	        │    └───catch
	        │       |───controllers
                │       │       UserControllerTest.java
	        │       │       VehicleControllerTest.java
	        │       │       BookingControllerTest.java
                │       │       CostControllerTest.java
	        │       │       GlobalExceptionHandlerTest.java
                │       │ 
	        │       ├───data
	        │       │       UserJdbcTemplateRepositoryTest.java
	        │       │       VehicleJdbcTemplateRepositoryTest.java
                │       │       BookingJdbcTemplateRepositoryTest.java
	        │       │       CostJdbcTemplateRepositoryTest.java
                │       │       KnownGoodState.java
                │       │
                │       └───domain
                │                UserServiceTest.java
                │                VehicleServiceTest.java
                │                BookingServiceTest.java
                │                CostServiceTest.java
                │
                │ 
                └───resources- > application.properties

```

#### Database Schema

![image](https://github.com/user-attachments/assets/a5263a3d-1331-4550-9c58-950c9b081203)


#### layer


#### Test Plan

### Wireframes: roughly sketch your UI and how one view transitions to another. You can also use design tools to create wireframes
