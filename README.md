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
1. Perform Final Testing (All Day) - All Devs
   
#### **Day 5 - Presentation**
1. Present Application (All Day) - All Devs
   
### Diagrams Maven: 
#### Class details & design

### Data
- **Booking**: available if a car exists and has not been booked already
- **Booking_status**: determines if the car is booked by user
- **Cars**: name that identifies
- **Make**: company name
- **Model**: car's model
- **Years**: the number of car years
- **Color**: color of car
- **Trim**: spacific tear of car

**Booking_type** is a Java enum, INT NOT NULL, -- 1 for leased, 2 for rented 

### Validation

- **Cars** is required and cannot be blank.
- **Make** is required and cannot be blank.
- **Model** is required and cannot be blank.
- **Year** can be no more than 6 months future year.
- **Color** is required or any color option?
- **Booking_type** is required.
- **Booking** may not be duplicated.

## Package/Class Overview
  
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

## High-level Requirements and Test Plan
- As Admin:
   - display all cars, all users, all bookings, all locations
   - add car, user, booking, location
   - edit car, user, booking, location
   - delete: car, user, booking, location
- As User:
   - display all cars
   - add booking
   - edit booking
   - delete booking
   - edit the user account and delete an account
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

## Controller Perspectives

### View All Cars
1. use the vehicle service to fetch available cars by name.
2. pass the list to the view to display all available cars.
3. use the booking service to display cars that are currently booked.

### Make Booking Car
1. collect booking details from the view (car, user, location, start date, end date, booking type).
2. use the service to add the booking and return the result (success/failure).
3. display booking confirmation or error message in the view.

### Update Booking Car
1. collect the booking ID from the view.
2. use the service to fetch booking details by ID.
3. display booking data in the view and allow the user to make changes.
4. collect updated values and call setters in the view.
5. use the service to update and save the booking
6. display the result (success or failure) in the view.

### View All Bookings
1. use the service to retrieve all bookings (admin) or user-specific bookings (user).
2. display bookings with car, location, and status details in the view.

### Edit Bookings
1. select a booking from the list (via view).
2. use the service to fetch full booking details.
3. allow user/admin to edit fields like date, type, location.
4. validate changes and update via the service.
5. display the updated result or any errors.

### Delete Bookings
1. select a booking to delete.
2. confirm deletion in the view.
4. use the service to delete booking by ID.
5. display success or failure in the view.

### Add Car by Admin
1. collect new car details from admin (make, model, year, color, trim, availability).
2. use the service to add the car.
3. display success message or validation errors.

### Edit Car by Admin
1. select a car to edit.
2. use the service to fetch current car details.
3. allow admin to modify car info in the view.
4. save changes using the service.
5. display the result in the view.

### Delete Car by Admin
1. select a car from the list.
2. confirm deletion in the view.
3. use the service to delete the car.
4. display a success or failure message.

### View All Locations by Admin
1. use the service to fetch all location records.
2. display list of locations (city, state, year) in the view.

### Edit Location by Admin
1. select a location to edit.
2. fetch location details using the service.
3. modify values via the view.
4. save changes with the service.
5. show the update result in the view.

### Delete Location by Admin
1. select a location to delete.
2. confirm deletion in the view.
3. use the service to delete location by ID.
4. display success or failure in the view.

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

On the Home page can see all cars even it already booked.

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

<img width="740" alt="admin" src="https://github.com/user-attachments/assets/6fc24fd9-a3c0-4abf-9f26-33ef32eac8fe" />

```
Clicking:
- "All Booking Cars" → list of booked cars by user, where the admin can edit, delete bookings, and add booking
- "All Users" → admin can add the user, edit, and delete
- "All Available Locations" -> list of available locations 
- "All Cars" → admin can add the car, edit, and delete
```

#### View the Add Booking page

```
+---------------------------------------------------+  
|                     Add Booking                   |  
+---------------------------------------------------+  
| Car ID: [_____________________________]           |  
| User ID: [_____________________________]          |  
| Location ID: [_____________________________]      |  
| Start Date: [ mm/dd/yyyy ]                        |  
| End Date: [ mm/dd/yyyy ]                          |
| Booking Type: Lease or Rent                       |  
+---------------------------------------------------+  
|                  [ Submit Booking ]               |  
+---------------------------------------------------+

After submit button will showing the car -> Booked car with today's date and status will be changed to booked
```

#### View the Available Cars page
```
+---------------------------------------------------+  
|                    Available Cars                 |  
+---------------------------------------------------+  
|   Card 1         |   Card 2         |   Card 3     |  
|   +--------+     |   +--------+     |   +--------+ |  
|   |  Car 1 |     |   |  Car 2 |     |   |  Car 3 | |  
|   |   Img  |     |   |   Img  |     |   |   Img  | |  
|   | Make:  |     |   | Make:  |     |   | Make:  | |  
|   | Model: |     |   | Model: |     |   | Model: | |  
|   | Year:  |     |   | Year:  |     |   | Year:  | |  
|   +--------+     |   +--------+     |   +--------+ |  
|   [ Book Now ]   |   [ Book Now ]   |   [ Book Now ] |  
+---------------------------------------------------+  
|   Card 4         |   Card 5         |   Card 6     |  
|   +--------+     |   +--------+     |   +--------+ |  
|   |  Car 4 |     |   |  Car 5 |     |   |  Car 6 | |  
|   |   Img  |     |   |   Img  |     |   |   Img  | |  
|   | Make:  |     |   | Make:  |     |   | Make:  | |  
|   | Model: |     |   | Model: |     |   | Model: | |  
|   | Year:  |     |   | Year:  |     |   | Year:  | |  
|   +--------+     |   +--------+     |   +--------+ |  
|   [ Book Now ]   |   [ Book Now ]   |   [ Book Now ] |  
+---------------------------------------------------+
etc
```
