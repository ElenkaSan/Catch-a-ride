# Catch-a-ride
Catch A Ride is the premier application for catching your next ride. Whether you need to lease or rent a car, Catch A Ride is here to meet all your vehicular supply needs.
Catch A Ride opens up multiple avenues to obtain your next vehicle and offloads your inventory as well. Users can create an account and then browse from a wide selection of vehicles. Here, a user may see listings in their local area and then, when the options allow, rent or lease a vehicle.
Our service offers a fast and efficient portal for any user to obtain vehicles with minimal effort.

### Work Plan:  **Daily Schedule**

# **Day 1**
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
9. Create Booking Type Enums (12 minutes)
### **Data Layer**
9. Build User Repository (30 minutes)
11. Build Data Exception (15 minutes)
12. Build Vehicle Repository (1 hour)
13. Build Booking Repository (1 hour)
14. Build Location Repository (1 hour)
15. Build Dealership Repository (1 hour)
### **Mappers**
18. Build User Mapper (15 minutes)
19. Build Location Mapper (15 minutes)
20. Build Booking Mapper (15 minutes)
21. Build Dealership Mapper (15 minutes)
22. Build Vehicle Mapper (15 minutes)

# **Day 2**
1. Add Repository Unit Tests (2 hours)
### **Service Layer**
2. Build Result (1 hour)
3. Build Vehicle Service (1 hour)
4. Build Booking Service (1 hour)
5. Build Location Service (1 hour)
6. Build Dealership Service (1 hour)
### **Security**
7. Build User Service (1 hour)
8. Build Security Config (1 hour)
9. Add Service Unit Tests (2 hours)
### **Controllers**
10. Build Error Response (15 minutes)
11. Build Global Exception Handler (15 minutes)
12. Build Authorization Controller (30 minutes)
13. Build User Controller (30 minutes)
14. Build Vehicle Controller (30 minutes)
15. Build Booking Controller (30 minutes)
16. Build Location Controller (30 minutes)
17. Build Dealership Controller (30 minutes)

# **Day 3**
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

# **Day 4**
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
        │   │           │       AuthController.java
	│   │           │       VehicleController.java
	│   │           │       BookingController.java
	│   │           │       LocationController.java
        │   │           │       DealershipController.java
	│   │           │
	│   │           |───data
        │   │           │      └───mapper -> each one has mapper.java
        │   │           │      │              
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
	│   │           │       VehicleService.java
	│   │           │       BookingService.java
	│   │           │       Location.java
	│   │           │       Response.java
	│   │           │       Result.java
        │   │           │       ResultType.java
        │   │           │       DealershipService.java
	│   │           │
	│   │           |───models
	│   │           |       User.java
	│   │           |       Vehicle.java
	│   │           |       Booking.java
	│   │           |       Location.java
        |   |           |       Dealership.java
        |   │           │
        │   │           └───security
        │   |                   UserService.java
        │   |                   SecurityConfig.java
	│   │
	│   └───resources
        │            │   application.properties
        │            │
        │            └───templates
        │                 │     delete.html
        │                 │     form.html
        │                 │     index.html
        │                 │     not-found.html
        │                 │
        │                 └───security
        │                          login.html
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
                └───resources
                         application.properties

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

<img width="600" alt="Screenshot 2025-04-11 at 10 46 58 AM" src="https://github.com/user-attachments/assets/37bd09b2-9b36-4474-b780-de8ee9db9622" />

#### Can search a car viathe the Search bar
```
Search a car by model, year, dealership, and location
```

#### View the page for Admin

<img width="620" alt="Screenshot 2025-04-11 at 10 59 06 AM" src="https://github.com/user-attachments/assets/8e28264c-2b54-4976-827d-5f9cf5e8430b" />

#### CRUD

```
Clicking:
- "All Booking Cars" → list of booked cars by user, where the admin can edit, delete bookings, and add booking
- "All Users" → admin can add the user, edit, and delete
- "All Available Locations" -> list of available locations 
- "All Cars" → admin can add the car, edit, and delete
```
#### View All Booking Cars

<img width="620" alt="Screenshot 2025-04-11 at 11 08 46 AM" src="https://github.com/user-attachments/assets/d2855a0d-6b29-487c-bf87-784cd71e55e7" />

- Add a new Booking Cars (the same as what the user has to add a Booking car)
  
<img width="330" alt="Screenshot 2025-04-11 at 1 51 56 PM" src="https://github.com/user-attachments/assets/f2c25ece-7e58-46b0-9214-8bc0095b86d6" />

- Edit Booking Car
  
<img width="359" alt="Screenshot 2025-04-11 at 1 52 01 PM" src="https://github.com/user-attachments/assets/3bba7bca-7665-4ae1-bbee-dc0da45dd2f2" />

- Delete Booking Car
  
<img width="620" alt="Screenshot 2025-04-11 at 1 55 40 PM" src="https://github.com/user-attachments/assets/bff5d58d-301e-4424-bbf8-f38c5aed83d4" />

#### View All Users, add, edit and delete

<img width="622" alt="Screenshot 2025-04-11 at 2 37 50 PM" src="https://github.com/user-attachments/assets/dfa55ac3-559e-4d10-8103-c1475094075e" />

- Add User
  
<img width="324" alt="Screenshot 2025-04-11 at 2 46 42 PM" src="https://github.com/user-attachments/assets/1d0219a0-954a-4f05-b0f5-ba38d2f400c5" />

- Edit User

<img width="324" alt="Screenshot 2025-04-11 at 2 41 15 PM" src="https://github.com/user-attachments/assets/d1668828-b989-4e3e-89b3-2e9a4fa682a5" />


#### View All Available Locations

<img width="634" alt="Screenshot 2025-04-11 at 2 30 01 PM" src="https://github.com/user-attachments/assets/c905f038-2229-4ac4-8b94-95242eb432a2" />


#### View All Cars, add, edit, and delete

<img width="620" alt="Screenshot 2025-04-11 at 2 19 00 PM" src="https://github.com/user-attachments/assets/ad2a23b3-e909-4cbf-9107-21e1b89daa4a" />

- Add a car
  
<img width="330" alt="Screenshot 2025-04-11 at 2 47 07 PM" src="https://github.com/user-attachments/assets/c9760fba-5a75-467a-9da9-13ebe045ddaf" />

- Edit a car

<img width="330" alt="Screenshot 2025-04-11 at 2 25 15 PM" src="https://github.com/user-attachments/assets/1cb2f05c-af98-4617-817b-5ff67e42926e" />

#### View the Add Booking page

```
After submitting button will show the car -> Booked car with today's date, will dispay the dealership name, and the status will be changed to booked
```

<img width="334" alt="Screenshot 2025-04-11 at 1 51 56 PM" src="https://github.com/user-attachments/assets/d6266c63-69b0-4976-a4f6-fbbe1abf94b8" />

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

<img width="620" alt="Screenshot 2025-04-11 at 11 48 55 AM" src="https://github.com/user-attachments/assets/7f9c1594-1305-459f-a5ac-d6cc9caf4152" />


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

<img width="340" alt="Screenshot 2025-04-11 at 1 51 46 PM" src="https://github.com/user-attachments/assets/4670762e-628e-4366-a0c4-775582375a7b" />

<img width="326" alt="Screenshot 2025-04-11 at 2 39 54 PM" src="https://github.com/user-attachments/assets/69b15b66-d6b1-4734-8fd6-b10aade58eb0" />

#### View User page with edit and delete options
```
If there are no booked cars:
+---------------------------------------------------+  
|              "You have no cars booked yet."       |  
+---------------------------------------------------+ 
                 OR
If they booked a car: Show already booked cars
+---------------------------------------------------+  
|                    Hello ‘Username’!               |
|             You have booked these Cars             |  
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

<img width="613" alt="Screenshot 2025-04-11 at 2 07 25 PM" src="https://github.com/user-attachments/assets/1a364fec-1328-42d8-9ea4-27fa4256c1a4" />

- Book a new car
  
<img width="330" alt="Screenshot 2025-04-11 at 2 50 58 PM" src="https://github.com/user-attachments/assets/77b893eb-cc17-40df-b437-478589b99330" />

- Edit booked car
  
<img width="329" alt="Screenshot 2025-04-11 at 1 52 01 PM" src="https://github.com/user-attachments/assets/e7b1320c-f688-4f5d-9741-b540512e56c6" />

#### Error Page

```
+---------------------------------------------------+  
|              404 Error                            |  
+---------------------------------------------------+  
```

<img width="569" alt="Screenshot 2025-04-11 at 2 44 51 PM" src="https://github.com/user-attachments/assets/fd2d4b01-bbf5-4331-b021-dad9757323a5" />
