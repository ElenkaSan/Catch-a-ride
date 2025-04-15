package learn.catch_ride.domain;

import learn.catch_ride.data.LocationRepository;
import learn.catch_ride.models.Location;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) { this.locationRepository = locationRepository; }

    public List<Location> findAll() { return locationRepository.findAll(); }

    public Location findById(int locationId) { return locationRepository.findById(locationId); }

    public Result<Location> add(Location location) {
        Result<Location> result = validate(location);
        if (!result.isSuccess()) {
            return result;
        }


        location = locationRepository.add(location);
        result.setPayload(location);
        return result;
    }

    public Result<Location> update(Location location) {
        Result<Location> result = validate(location);
        if (!result.isSuccess()) {
            return result;
        }

        locationRepository.update(location);

        result.setPayload(location);

        return result;
    }

    public Result<Location> deleteById(int locationId) {
        Result<Location> result = new Result<>();
        Location location = findById(locationId);

        if (locationRepository.getUsageCount(locationId) > 0) {
            result.addMessage("Cannot delete location that is referenced in other tables.", ResultType.INVALID);
            result.setPayload(location);
            return result;
        }



        locationRepository.deleteById(locationId);

        result.setPayload(location);

        return result;
    }


    private Result<Location> validate(Location location) {
        Result<Location> result = new Result<>();
        List<Location> locations = locationRepository.findAll();

        if (location == null) {
            result.addMessage("location cannot be null.", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(location.getAddress())) {
            result.addMessage("Address is required.", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(location.getCity())) {
            result.addMessage("City is required.", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(location.getState())) {
            result.addMessage("State is required.", ResultType.INVALID);
        }

        if (location.getZipCode() == 0) {
            result.addMessage("Zip code is required.", ResultType.INVALID);
        }



        for(Location l: locations) {
            if(l.getLocationId() == location.getLocationId()){
                continue;
            }
            if(l.equals(location)) {
                result.addMessage("Cannot have duplicate locations.", ResultType.INVALID);
                break;
            }
        }

        return result;

    }
}
