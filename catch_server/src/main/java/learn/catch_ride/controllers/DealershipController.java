package learn.catch_ride.controllers;

import learn.catch_ride.domain.DealershipService;
import learn.catch_ride.domain.Result;
import learn.catch_ride.models.Dealership;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/dealership")
public class DealershipController {
    public DealershipController(DealershipService service) {
        this.service = service;
    }

    private final DealershipService service;

    @GetMapping
    public List<Dealership> findAll() { return service.findAll(); }
    //public ResponseEntity<List<Dealership>> findAll() {
    //        List<Dealership> dealership = service.findAll();
    //        return new ResponseEntity<>(dealership, HttpStatus.OK);
    //    }


    @GetMapping("/{dealershipId}")
    public Dealership findById(@PathVariable int dealershipId) { return service.findById(dealershipId); }
    //    public ResponseEntity<Dealership> findById(@PathVariable int dealershipId) {
    //        Dealership dealership = service.findById(dealershipId);
    //        if (dealership == null) {
    //            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    //        }
    //        return ResponseEntity.ok(dealership);
    //    }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Dealership dealership) {
        Result<Dealership> result = service.add(dealership);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{dealershipId}")
    public ResponseEntity<Object> update(@PathVariable int dealershipId, @RequestBody Dealership dealership) {
        if(dealershipId != dealership.getDealershipId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Dealership> result = service.update(dealership);
        if(result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{dealershipId}")
    public ResponseEntity<Void> deleteById(@PathVariable int dealershipId) {
        if (service.deleteById(dealershipId).isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
