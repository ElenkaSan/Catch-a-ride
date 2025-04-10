# Catch-a-ride
Catch A Ride is the premier application for catching your next ride. Whether you need to lease or rent a car, Catch A Ride is here to meet all your vehicular supply needs.
Catch A Ride opens up multiple avenues to obtain your next vehicle and offloads your inventory as well. Users can create an account and then browse from a wide selection of vehicles. Here, a user may see listings in their local area and then, when the options allow, rent or lease a vehicle.
Our service offers a fast and efficient portal for any user to obtain vehicles with minimal effort.

### Work Plan:  **Schedule**
## **Back End Development**
### **Project Setup**
1. Create a Maven Project (15 minutes)
2. Add Spring DI (15 minutes)
3. Build Database (30 minutes)
### **Models**
4. Create User Model (12 minutes)
5. Create Vehicle Model (12 minutes)
6. Create Booking Model (12 minutes)
7. Create Location Model (12 minutes)
8. Create Dealership Model (12 minutes)
### **Data Layer**
9. Build User Repository (30 minutes)
10. Build Vehicle Repository (1 hour)
11. Build Booking Repository (1 hour)
12. Build Location Repository (1 hour)
13. Build Dealership Repository (1 hour)
14. Build Error Response (15 minutes)
15. Build Global Exception Handler (15 minutes)
16. Build Authorization Controller (30 minutes)
17. Build User Controller (30 minutes)
18. Build Vehicle Controller (30 minutes)
19. Build Booking Controller (30 minutes)
20. Build Location Controller (30 minutes)
21. Build Dealership Controller (30 minutes)
22. Build User Mapper (15 minutes)
23. Build Location Mapper (15 minutes)
24. Build Booking Mapper (15 minutes)
25. Build Dealership Mapper (15 minutes)
26. Build Vehicle Mapper (15 minutes)
27. Add Repository Unit Tests (2 hours)
### **Service Layer**
28. Build User Service (1 hour)
29. Build Vehicle Service (1 hour)
30. Build Booking Service (1 hour)
31. Build Location Service (1 hour)
32. Build Dealership Service (1 hour)
33. Add Service Unit Tests (2 hours)
## **Front End Development**
1. Navigation (2 hours)
2. Login Page (2 hours)
3. Admin View (2 hours)
3. User View (2 hours)
2. Display Bookings (1 Hour)
3. Add Booking (1 Hour)
4. Display Users (1 Hour)
5. Add Users (1 Hour)
6. Update Listing (2 Hours)
7. Update Users (2 Hours)
8. Delete Listing (1 Hour)
9. Delete User (1 Hour)
## **Refinement and Final Testing**
1. Clean up Code (1 hour)
2. Ensure Proper Comments (1 hour)
3. Iron Out Bugs (1 hour)
4. Perform Final Testing (1 hour)
   
### Diagrams Maven: 
#### Class details & design

### App
- `public static void main(String[])` -- instantiate all required classes with valid arguments, dependency injection. run controller



D A T A   L A Y E R

### data.mapper.BookingMapper

- `public Booking mapRow(ResultSet resultSet, int i) throws SQLException`


### data.mapper.VehicleMapper

- `public Vehicle mapRow(ResultSet resultSet, int i) throws SQLException`


### data.mapper.LocationMapper

- `public Location mapRow(ResultSet resultSet, int i) throws SQLException`


### data.mapper.DealershipMapper

- `public Dealership mapRow(ResultSet resultSet, int i) throws SQLException`


### data.mapper.UserMapper

- `public User mapRow(ResultSet resultSet, int i) throws SQLException`


### data.BookingJdbcTemplateRepository
- `List<Booking> findAll(String)`
- `Booking findById(int)`
- `Booking add(Booking)`
- `boolean update(Booking)`
- `boolean deleteById(int)`

### data.BookingRepository implements BookingJdbcTemplateRepository

### data.VehicleJdbcTemplateRepository
- `List<Vehicle> findAll(String)`
- `Vehicle findById(int)`
- `Vehicle add(Vehicle)`
- `boolean update(Vehicle)`
- `boolean deleteById(int)`

### data.VehicleRepository implements VehicleJdbcTemplateRepository

### data.LocationJdbcTemplateRepository
- `List<Location> findAll(String)`
- `Location findById(int)`
- `Location add(Location)`
- `boolean update(Location)`
- `boolean deleteById(int)`

### data.LocationgRepository implements LocationJdbcTemplateRepository

### data.DealershipJdbcTemplateRepository
- `List<Dealership> findAll(String)`
- `Dealership findById(int)`
- `Dealership add(Dealership)`
- `boolean update(Dealership)`
- `boolean deleteById(int)`

### data.DealershipRepository implements DealershipJdbcTemplateRepository

### data.UserJdbcTemplateRepository
- `List<User> findAll(String)`
- `User findById(int)`
- `User add(User)`
- `boolean update(User)`
- `boolean deleteById(int)`

### data.UserRepository implements UserJdbcTemplateRepository


D O M A I N    L A Y E R

### domain.ResultType
An enum with three values: SUCCESS, INVALID or NOT_FOUND

### domain.Result
- `private final ArrayList<String> messages` -- error messages
- `private T payload` -- an optional payload
- `private ResultType type = ResultType.SUCCESS;` -- enum
- `public boolean isSuccess()` -- calculated getter, true if no error messages
- `public List<String> getMessages()` -- messages getter, create a new list
- `public T getPayload()` -- payload getter
- `public void setPayload(T)` -- payload setter
- `public void addMessage(String, ResultType)` -- adds an error message to messages

### domain.BookingService
-  `private BookingRepository repository` -- required data dependency
-  `public BookingService(BookingRepository)` -- constructor
-  `public List<Booking> findAll(String)` -- pass-through to repository
-  `public Booking findById(int)` -- pass-through to repository
-  `public BookingResult add(Booking)` -- validate, then add via repository
-  `public BookingResult update(Booking)` -- validate, then update via repository
-  `public BookingResult deleteById(int)` -- pass-through to repository
-  `private BookingResult validate(Booking)` -- general-purpose validation routine


### domain.VehicleService
-  `private VehicleRepository repository` -- required data dependency
-  `public VehicleService(VehicleRepository)` -- constructor
-  `public List<Vehicle> findAll(String)` -- pass-through to repository
-  `public Vehicle findById(int)` -- pass-through to repository
-  `public VehicleResult add(Vehicle)` -- validate, then add via repository
-  `public VehicleResult update(Vehicle)` -- validate, then update via repository
-  `public VehicleResult deleteById(int)` -- pass-through to repository
-  `private VehicleResult validate(Vehicle)` -- general-purpose validation routine

### domain.LocationService
-  `private LocationRepository repository` -- required data dependency
-  `public LocationService(LocationRepository)` -- constructor
-  `public List<Location> findAll(String)` -- pass-through to repository
-  `public Location findById(int)` -- pass-through to repository
-  `public LocationResult add(Location)` -- validate, then add via repository
-  `public LocationResult update(Location)` -- validate, then update via repository
-  `public LocationResult deleteById(int)` -- pass-through to repository
-  `private LocationResult validate(Location)` -- general-purpose validation routine

### domain.DealershipService
-  `private DealershipRepository repository` -- required data dependency
-  `public DealershipService(DealershipRepository)` -- constructor
-  `public List<Dealership> findAll(String)` -- pass-through to repository
-  `public Dealership findById(int)` -- pass-through to repository
-  `public DealershipResult add(Dealership)` -- validate, then add via repository
-  `public DealershipResult update(Dealership)` -- validate, then update via repository
-  `public DealershipResult deleteById(int)` -- pass-through to repository
-  `private DealershipResult validate(Dealership)` -- general-purpose validation routine

### domain.UserService
-  `private UserRepository repository` -- required data dependency
-  `public UserService(UserRepository)` -- constructor
-  `public List<User> findAll(String)` -- pass-through to repository
-  `public User findById(int)` -- pass-through to repository
-  `public UserResult add(User)` -- validate, then add via repository
-  `public UserResult update(User)` -- validate, then update via repository
-  `public UserResult deleteById(int)` -- pass-through to repository
-  `private UserResult validate(User)` -- general-purpose validation routine


C O N T R O L L E R S

### controller.ErrorResponse
-  `private final LocalDateTime timestamp` -- registering time of error
-  `private final String message` -- error message
-  `public String getTimestamp()`
-  `public String getMessage()` 
-  `public ErrorResponse(String message)` -- constructor
-  `public static ResponseEntity<ErrorResponse> build(String message)` -- http status and error message
-  `public static <T> ResponseEntity<Object> build(Result<T> result)` -- getting http error from result type

### controller.GlobalExceptionHandler
-  `public ResponseEntity<ErrorResponse> handleException(DataIntegrityViolationException ex)` -- handling Data Integrity errors
-  `public ResponseEntity<ErrorResponse> handleException(IllegalArgumentException ex)` -- handling Illegal Argument exceptions
-  `public ResponseEntity<ErrorResponse> handleException(DataAccessException ex)` -- handling Data Access exceptions
-  `public ResponseEntity<ErrorResponse> handleException(Exception ex) throws Exception` -- handling general exceptions

### controller.BookingController
-  `private final BookingService service;` -- required service dependency
-  `public BookingController(BookingService service)` -- constructor
-  `public List<Booking> findAll()` -- pass-through to service
-  `public Booking findById(int bookingId)` -- pass-through to service
-  `public ResponseEntity<Object> add(Booking booking)` -- give error response in case of failure
-  `public ResponseEntity<Object> update(int bookingId, Booking booking)` -- validate update and return appropriate response if failure
-  `public ResponseEntity<Void> deleteById(int bookingId)` -- return either no content or not found in case of error

### controller.VehicleController
-  `private final VehicleService service;` -- required service dependency
-  `public VehicleController(VehicleService service)` -- constructor
-  `public List<Vehicle> findAll()` -- pass-through to service
-  `public Vehicle findById(int vehicleId)` -- pass-through to service
-  `public ResponseEntity<Object> add(Vehicle vehicle)` -- give error response in case of failure
-  `public ResponseEntity<Object> update(int vehicleId, Vehicle vehicle)` -- validate update and return appropriate response if failure
-  `public ResponseEntity<Void> deleteById(int vehicleId)` -- return either no content or not found in case of error

### controller.LocationController
-  `private final LocationService service;` -- required service dependency
-  `public LocationController(LocationService service)` -- constructor
-  `public List<Location> findAll()` -- pass-through to service
-  `public Location findById(int locationId)` -- pass-through to service
-  `public ResponseEntity<Object> add(Location location)` -- give error response in case of failure
-  `public ResponseEntity<Object> update(int locationId, Location location)` -- validate update and return appropriate response if failure
-  `public ResponseEntity<Void> deleteById(int locationId)` -- return either no content or not found in case of error

### controller.DealershipController
-  `private final DealershipService service;` -- required service dependency
-  `public DealershipController(DealershipService service)` -- constructor
-  `public List<Dealership> findAll()` -- pass-through to service
-  `public Dealership findById(int dealershipId)` -- pass-through to service
-  `public ResponseEntity<Object> add(Dealership dealership)` -- give error response in case of failure
-  `public ResponseEntity<Object> update(int dealershipId, Dealership dealership)` -- validate update and return appropriate response if failure
-  `public ResponseEntity<Void> deleteById(int dealershipId)` -- return either no content or not found in case of error

### controller.UserController
-  `private final UserService service;` -- required service dependency
-  `public UserController(UserService service)` -- constructor
-  `public List<User> findAll()` -- pass-through to service
-  `public User findById(int userId)` -- pass-through to service
-  `public ResponseEntity<Object> add(User user)` -- give error response in case of failure
-  `public ResponseEntity<Object> update(int userId, User user)` -- validate update and return appropriate response if failure
-  `public ResponseEntity<Void> deleteById(int userId)` -- return either no content or not found in case of error



M O D E L S

### models.BookingType

An enum with two values: Rented or Leased

### models.Booking
- `private int id`
- `private int vehicleId`
- `private int userId`
- `private int locationId`
- `private LocalDate startDate`
- `private LocalDate endDate`
- `private boolean bookingStatus`
- `private BookingType bookingType`
- `private LocalDate dateCreated`
- Full getters and setters
- override `equals` and `hashCode`

### models.Location
- `private int id`
- `private String city`
- `private String state`
- `private int zipCode`
- Full getters and setters
- override `equals` and `hashCode`

### models.User
- `private int id`
- `private String firstName`
- `private String lastName`
- `private String email`
- `private String password`
- `private LocalDate dateCreated`
- `private String address`
- `private Boolean isAdmin`
- Full getters and setters
- override `equals` and `hashCode`

### models.Vehicle
- `private int id`
- `private String make`
- `private String model`
- `private int year`
- `private String color`
- `private String trim`
- `private int dealershipId`
- Full getters and setters
- override `equals` and `hashCode`

### models.Dealership
- `private int id`
- `private String name`
- `private String description`
- `private int locationId`
- Full getters and setters
- override `equals` and `hashCode`
### Data
- **Booking**: a booking is valid only if the selected car exists and is currently marked as available (is_available = true).
- **Booking_status**: determines if the car is booked by user.
- **Cars**: each car is uniquely identified by its make, model, and year.
- **Make**: the car manufacturer (e.g., Toyota, Ford, Honda).
- **Model**: the specific model of the car (e.g., Camry, Civic, Explorer).
- **Years**: the manufacturing year of the car.
- **Color**: color of the car
- **Trim**: specific variant or tier of a car model (e.g., SE, XLE, Sport).

**Booking_type** is a Java enum:
  - 1 for leased
  - 2 for rented 

### Validation

- **Cars** a car must be selected to make a booking; this field cannot be blank
- **Make** is required and cannot be blank.
- **Model** is required and cannot be blank.
- **Year**
   - required and must not be more than 6 months into the future from the current date
   - must be a valid year (e.g., between 2020 and the current year + 1)
- **Color** is required, or any color option from the list?
- **Trim** is optional but must follow valid naming conventions if provided
- **Booking_type** is required, must be one of the allowed enum values (1, 2)
- **Duplicate Booking** a user may not create multiple bookings for the same car with overlapping dates

#### Location-Based Validation
**Cars** shown by zip code search:
    - results must be filtered server-side based on the zip code provided by the user

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
        │   │           │       DealershipController.java
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
        │   │           │       DealershipJdbcTemplateRepository.java
	│   │           │       DealershipRepository.java
	│   │           │
	│   │           |───domain
	│   │           │       UserService.java
	│   │           │       VehicleService.java
	│   │           │       BookingService.java
	│   │           │       Location.java
	│   │           │       Response.java
	│   │           │       Result.java
        │   │           │       ResultType.java
        │   │           │       DealershipService.java
	│   │           │
	│   │           └───models
	│   │                   User.java
	│   │                   Vehicle.java
	│   │                   Booking.java
	│   │                   Location.java
        |   |                   Dealership.java   
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
                │       │       DealershipControllerTest.java
	        │       │       GlobalExceptionHandlerTest.java
                │       │ 
	        │       ├───data
	        │       │       UserJdbcTemplateRepositoryTest.java
	        │       │       VehicleJdbcTemplateRepositoryTest.java
                │       │       BookingJdbcTemplateRepositoryTest.java
	        │       │       LocationJdbcTemplateRepositoryTest.java
                │       │       DealershipJdbcTemplateRepositoryTest.java
                │       │       KnownGoodState.java
                │       │
                │       └───domain
                │                UserServiceTest.java
                │                VehicleServiceTest.java
                │                BookingServiceTest.java
                |                BookingType.java
                │                LocationServiceTest.java
                │                DealershipServiceTest.java
                │ 
                └───resources- > application.properties

```

#### Database Schema

![catch_ride_db](https://github.com/user-attachments/assets/c4e2be91-d85c-4a15-a17f-77e25f488877)



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
  Catch Ride         [ Home | Available Cars | Add Booking | Login/Signup | Seaching bar ]
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

<img width="557" alt="Screenshot 2025-04-10 at 5 42 53 PM" src="https://github.com/user-attachments/assets/dff5f600-b139-4692-9a56-0827b189d433" />

#### View the page for Admin

<img width="740" alt="admin" src="https://github.com/user-attachments/assets/b441cb82-ce99-4d73-b9a6-474927125629" />


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

After submitting button will show the car -> Booked car with today's date, and the status will be changed to booked
```

<img src="https://github.com/user-attachments/assets/cc82c08a-3442-409c-a97f-597209652642" width="600" />


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
|  [ Book Now ]    |  [ Book Now ]    | [ Book Now ] | 
+---------------------------------------------------+  
|   Card 4         |   Card 5         |   Card 6     |  
|   +--------+     |   +--------+     |   +--------+ |  
|   |  Car 4 |     |   |  Car 5 |     |   |  Car 6 | |  
|   |   Img  |     |   |   Img  |     |   |   Img  | |  
|   | Make:  |     |   | Make:  |     |   | Make:  | |  
|   | Model: |     |   | Model: |     |   | Model: | |  
|   | Year:  |     |   | Year:  |     |   | Year:  | |  
|   +--------+     |   +--------+     |   +--------+ |  
|  [ Book Now ]    |  [ Book Now ]    | [ Book Now ] |  
+---------------------------------------------------+
Loading more when scrolling down
```

<img width="557" alt="Screenshot 2025-04-10 at 5 42 53 PM" src="https://github.com/user-attachments/assets/230acbbe-4df6-493b-9844-b46a1f90fa7e" />


#### View Login/Signup 
```
+---------------------------------------------------+  
|                    Login                          |  
+---------------------------------------------------+  
| Username: [_____________________________]         |  
| Password: [_____________________________]         | 
+---------------------------------------------------+  
|                  [ Login ]                        |  
+---------------------------------------------------+

OR

+---------------------------------------------------+  
|                     Signup                        |  
+---------------------------------------------------+  
| First_name: [_____________________________]       |  
| Last Name: [_____________________________]        |  
| Username: [_____________________________]         |  
| Email: [_____________________________]            |  
| Address: [_____________________________]          |  
+---------------------------------------------------+  
|                  [ Signup ]               |  
+---------------------------------------------------+

```
<img src="https://github.com/user-attachments/assets/80ef2e2d-bbfa-46c7-b18c-12c355eb0453" width="600" />


#### View Search bar

![IMG_09609DDB2AE0-1](https://github.com/user-attachments/assets/c7a5bc17-b328-4d53-8f00-0b3625854c1b)

```
Search a car by model, year, dealership, and location
```

<img src="https://github.com/user-attachments/assets/0c3696e4-fd27-4611-bae6-fed61bf0cc59" width="600" />

#### View User page 
```
If there are no booked cars:
+---------------------------------------------------+  
|              "You have no cars booked yet."       |  
+---------------------------------------------------+ 
                 OR
If they booked a car: Show already booked cars
+---------------------------------------------------+  
|                    Your booked cars                 |  
+---------------------------------------------------+  
|   Card 1         |   Card 2         |   Card 3     |  
|   +--------+     |   +--------+     |   +--------+ |  
|   |  Car 1 |     |   |  Car 2 |     |   |  Car 3 | |  
|   |   Img  |     |   |   Img  |     |   |   Img  | |  
|   | Make:  |     |   | Make:  |     |   | Make:  | |  
|   | Model: |     |   | Model: |     |   | Model: | |  
|   | Year:  |     |   | Year:  |     |   | Year:  | |  
|   +--------+     |   +--------+     |   +--------+ |  
| [ Booked Date]   | [ Booked Date]   |[ Booked Date]| 
+---------------------------------------------------+  
```

#### Error Page

```
+---------------------------------------------------+  
|              404 Error                            |  
+---------------------------------------------------+  
```

