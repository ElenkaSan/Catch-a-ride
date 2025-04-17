package learn.catch_ride.controllers;

import learn.catch_ride.domain.AwsService;
import learn.catch_ride.domain.Result;
import learn.catch_ride.domain.VehicleService;
import learn.catch_ride.models.Vehicle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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
    private final AwsService awsService;

    public VehicleController(VehicleService service, AwsService awsService) {
        this.service = service;
        this.awsService = awsService;
    }

    @GetMapping
    public List<Vehicle> findAll() { return service.findAll(); }

    @GetMapping("/id/{vehicleId}")
    public Vehicle findById(@PathVariable int vehicleId) { return service.findById(vehicleId); }

    @PostMapping
    public ResponseEntity<Object> add(@RequestPart("vehicle") Vehicle vehicle, @RequestPart(value = "file", required = false) MultipartFile multipartFile) {
        try {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                File file = File.createTempFile("vehicle-", multipartFile.getOriginalFilename());
                multipartFile.transferTo(file);

                String key = "vehicles/temp/" + multipartFile.getOriginalFilename();
                awsService.uploadFile(key, file);
                file.delete();

                String imageUrl = awsService.getPublicUrl(key);

                vehicle.setImageUrl(imageUrl);
            }

            Result<Vehicle> result = service.add(vehicle);
            if (result.isSuccess()) {
                return new ResponseEntity<>(result.getPayload(), HttpStatus.CREATED);
            }

            return ErrorResponse.build(result);

        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to upload image: " + e.getMessage());
        }
    }

    @PutMapping("/{vehicleId}")
    public ResponseEntity<Object> update(@PathVariable int vehicleId, @RequestPart("vehicle") Vehicle vehicle, @RequestPart(value = "file", required = false) MultipartFile multipartFile) {
        if(vehicleId != vehicle.getVehicleId()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        try {
            if (multipartFile != null && !multipartFile.isEmpty()) {
                File file = File.createTempFile("vehicle-", multipartFile.getOriginalFilename());
                multipartFile.transferTo(file);

                String key = "vehicles/" + vehicle.getVehicleId() + "/" + multipartFile.getOriginalFilename();
                awsService.uploadFile(key, file);
                file.delete();

                String imageUrl = awsService.getPublicUrl(key);
                vehicle.setImageUrl(imageUrl);
            } else {
                Vehicle existing = service.findById(vehicleId);
                vehicle.setImageUrl(existing.getImageUrl());
            }
            Result<Vehicle> result = service.update(vehicle);
            if (result.isSuccess()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return ErrorResponse.build(result);

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Upload failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/{vehicleId}")
    public ResponseEntity<Void> deleteById(@PathVariable int vehicleId) {
        if (service.deleteById(vehicleId).isSuccess()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
