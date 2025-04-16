package learn.catch_ride.controllers;

import learn.catch_ride.domain.Result;
import learn.catch_ride.domain.VehicleService;
import learn.catch_ride.models.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.UUID;


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

    @PostMapping("/upload-image")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            String uploadDir = "uploads/";
            String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
            Path path = Paths.get(uploadDir + fileName);
            Files.createDirectories(path.getParent());
            Files.write(path, file.getBytes());

            return ResponseEntity.ok("/uploads/" + fileName); // Return the public URL path to be stored in frontend db
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage()); //specific for img only
        }
    }

}
