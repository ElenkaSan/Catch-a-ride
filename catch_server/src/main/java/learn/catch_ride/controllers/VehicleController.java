package learn.catch_ride.controllers;

import learn.catch_ride.domain.Result;
import learn.catch_ride.domain.VehicleService;
import learn.catch_ride.models.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
@RequestMapping("/api/vehicle")
public class VehicleController {
    private final VehicleService service;

    public VehicleController(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Vehicle> findAll() { return service.findAll(); }

    @GetMapping("/id/{vehicleId}")
    public Vehicle findById(@PathVariable int vehicleId) { return service.findById(vehicleId); }

    @PostMapping
    public ResponseEntity<Object> add(@RequestBody Vehicle vehicle) {
        Result<Vehicle> result = service.add(vehicle);
        if (result.isSuccess()) {
            return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
        }
        return ErrorResponse.build(result);
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<Object> update(@PathVariable int vehicleId, @RequestBody Vehicle vehicle) {
        if(vehicleId != vehicle.getVehicleId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        Result<Vehicle> result = service.update(vehicle);
        if(result.isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ErrorResponse.build(result);
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteById(@PathVariable int vehicleId) {
        if (service.deleteById(vehicleId).isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
