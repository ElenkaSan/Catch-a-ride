package learn.catch_ride.controllers;

import learn.catch_ride.domain.BookingService;
import learn.catch_ride.domain.Result;
import learn.catch_ride.models.Booking;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/booking")
public class BookingController {

    private final BookingService service;

    public BookingController(BookingService service) { this.service = service; }

    @GetMapping
    public List<Booking> findAll() { return service.findAll(); }

    @GetMapping("/bookingId/{bookingId}")
    public Booking findById(@PathVariable int bookingId) { return service.findById(bookingId); }

    @GetMapping("/locationId/{locationId}")
    public List<Booking> findByLocationId(@PathVariable int locationId) { return service.findByLocation(locationId); }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Booking booking) {
        Result<Booking> result = service.add(booking);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{bookingId}")
    public ResponseEntity<Object> update(@PathVariable int bookingId, @RequestBody Booking booking) {
        if(bookingId != booking.getBookingId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Booking> result = service.update(booking);
        if(result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{bookingId}")
    public ResponseEntity<Void> deleteById(@PathVariable int bookingId) {
        if (service.deleteById(bookingId).isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
