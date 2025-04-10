# Catch-a-ride
Catch A Ride is the premier application for catching your next ride. Whether you need to sell, buy, lease, or even just rent a car, Catch A Ride is here to meet all your vehicular supply needs.
Catch A Ride not only opens up multiple avenues to obtain their next vehicle but also offloads their inventory as well. Users can create an account and then browse from a wide selection of vehicles. Here, a user may see listings in their local area and then when the options allow; rent, buy, or lease a vehicle.
Our service offers a fast and efficient portal for any user to circulate vehicles with minimal effort.

### Work Plan:  **Daily Schedule**
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
    
#### **Day 2 - Front End Development**
1. Navigation (2 Hours) - Dev 1
2. Display Listings (1 Hour) - Dev 2
3. Add Listing (1 Hour) - Dev 3
4. Display Users (1 Hour) - Dev 1
5. Add Users (1 Hour) - Dev 2
6. Update Listing (2 Hours) - Dev 3
7. Update Users (2 Hours) - Dev 2
8. Delete Listing (1 Hour) - Dev 1
9. Delete User (1 Hour) - Dev 3
10. Debug (4 Hours) - All Devs
    
#### **Day 3 - Refinement**
1. Clean up Code (2 Hours) - All Devs
2. Ensure Proper Comments (2 Hours) - All Devs
3. Iron Out Bugs (2 Hours) - All Devs
   
#### **Day 4 - Final Testing**
1. Preform Final Testing (All Day) - All Devs
   
#### **Day 5 - Presentation**
1. Present Application (All Day) - All Devs
   
### Diagrams Maven: 
#### Class details & design

```
       http
        ├───user.http
        |───booking.http
        ├───vehicle.http
        |───location.http
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
	│   │           │       LocationController.java
	│   │           │
	│   │           |───data
	│   │           │       DataException.java
	│   │           │       UsertJdbcTemplateRepository.java
	│   │           │       UserRepository.java
	│   │           │       VehicleJdbcTemplateRepository.java
	│   │           │       VehicleRepository.java
	│   │           │       BookingtJdbcTemplateRepository.java
	│   │           │       BookingRepository.java
	│   │           │       LocationJdbcTemplateRepository.java
	│   │           │       LocationRepository.java
	│   │           │
	│   │           |───domain
	│   │           │       UserService.java
	│   │           │       VehicleService.java
	│   │           │       BookingService.java
	│   │           │       Location.java
	│   │           │       Response.java
	│   │           │       Result.java
        │   │           │       ResultType.java
	│   │           │
	│   │           └───models
	│   │                   User.java
	│   │                   Vehicle.java
	│   │                   Booking.java
	│   │                   Location.java   
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
                │       │       LocationControllerTest.java
	        │       │       GlobalExceptionHandlerTest.java
                │       │ 
	        │       ├───data
	        │       │       UserJdbcTemplateRepositoryTest.java
	        │       │       VehicleJdbcTemplateRepositoryTest.java
                │       │       BookingJdbcTemplateRepositoryTest.java
	        │       │       LocationJdbcTemplateRepositoryTest.java
                │       │       KnownGoodState.java
                │       │
                │       └───domain
                │                UserServiceTest.java
                │                VehicleServiceTest.java
                │                BookingServiceTest.java
                │                LocationServiceTest.java
                │
                │ 
                └───resources- > application.properties

```

#### Database Schema

![image](https://github.com/user-attachments/assets/a5263a3d-1331-4550-9c58-950c9b081203)


#### Test Plan
- As Admin:
   - view all cars, all users
   - add car, user
   - edit car, user
   - delete: car, user, booking, location
- As User:
   - view all cars
   - booking a car
   - edit booking
   - delete booking
   - edit the user account and delete account
- Booking a Car:
   - `POST /api/bookings`
   - Request body: JSON representation of the booking
- Get All Bookings:
    `GET /api/bookings`
- Get Booking by ID:
    `GET /api/bookings/{bookingId}`
- Update Booking:
    `PUT /api/bookings/{bookingId}`
- Delete Booking:
    `DELETE /api/bookings/{bookingId}`

### Wireframes: roughly sketch your UI and how one view transitions to another. You can also use design tools to create wireframes

```
NAVBAR
  Catch Ride         [ Home | Available Cars | Add Booking | Login/Signup ]
                                     |
                                     v

                             [ HOME PAGE ]
                   -----------------------------------------------
                    |  Card 1  |  Card 2  |  Card 3  |  Card 4  |
                    | Book Now | Book Now | Book Now | Book Now |

                    |  Card 5  |  Card 6  |  Card 7  |  Card 8  |
                    | Book Now | Book Now | Book Now | Book Now |
                   -----------------------------------------------

Clicking:
- "Available Cars" → list of only `is_available = true` cars
- "Add Booking" → form to select car, dates, and user -> if no login, will send to login/signup page
- "Login" → login form, separate flows if admin

Each Card:
- Car Img, having Make, Model, Year

```

#### View the page for everyone

<img width="554" alt="cars" src="https://github.com/user-attachments/assets/1199777a-5005-4a0c-82d9-385817072a20" />

#### View the page for Admin

