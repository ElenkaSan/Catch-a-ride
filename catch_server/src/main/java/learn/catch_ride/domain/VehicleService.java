package learn.catch_ride.domain;

import learn.catch_ride.data.BookingRepository;
import learn.catch_ride.data.VehicleRepository;
import learn.catch_ride.models.Booking;
import learn.catch_ride.models.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleService {
    private final VehicleRepository vehicleRepository;
    private final BookingRepository bookingRepository;

    public VehicleService(VehicleRepository vehicleRepository, BookingRepository bookingRepository) {
        this.vehicleRepository = vehicleRepository;
        this.bookingRepository = bookingRepository;
    }

    public List<Vehicle> findAll() { return vehicleRepository.findAll(); }

    public Vehicle findById(int vehicleId) { return vehicleRepository.findById(vehicleId); }

    public List<Vehicle> findByDealershipId(int dealershipId) { return vehicleRepository.findByDealershipId(dealershipId); }

    //crud
    public Result<Vehicle> add(Vehicle vehicle){
        Result<Vehicle> result = validate(vehicle);
        if (!result.isSuccess()) {
            return result;
        }

        if (vehicle.getVehicleId() != 0) {
            result.addMessage("Vehicle Id cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        vehicle = vehicleRepository.add(vehicle);
        result.setPayload(vehicle);
        return result;
    }

    public Result<Vehicle> update(Vehicle vehicle){
        Result<Vehicle> result = new Result<>();
        if (!result.isSuccess()) {
            return result;
        }

        if (vehicle.getVehicleId() <= 0) {
            result.addMessage("Vehicle ID must be set for `update` operation", ResultType.INVALID);
            return result;
        }

        //fields that cannot be changed
        Vehicle updating = vehicleRepository.findById(vehicle.getVehicleId());
        if (updating == null) {
            result.addMessage("Vehicle not found", ResultType.NOT_FOUND);
            return result;
        }

        if (!updating.getMake().equals(vehicle.getMake())
                || !updating.getModel().equals(vehicle.getModel())
                || updating.getYear() != vehicle.getYear()
                || updating.getDealershipId() != vehicle.getDealershipId()) {
            result.addMessage("Make, model, year, and dealership cannot be changed.", ResultType.INVALID);
            return result;
        }

        if (vehicle.getRentRate() != null && vehicle.getRentRate().signum() < 0) {
            result.addMessage("Rent rate cannot be negative.", ResultType.INVALID);
        }

        if (vehicle.getLeaseRate() != null && vehicle.getLeaseRate().signum() < 0) {
            result.addMessage("Lease rate cannot be negative.", ResultType.INVALID);
        }

        vehicleRepository.update(vehicle);
        return result;
    }

    public Result<Vehicle> deleteById(int vehicleId){
        Result<Vehicle> result = new Result<>();

        List<Booking> bookings = bookingRepository.findAll().stream()
                .filter(b->b.getVehicleId() == vehicleId)
                .collect(Collectors.toList());

        if(!bookings.isEmpty()){
            result.addMessage("Cannot delete vehicle: it is currently booked.", ResultType.INVALID);
            return result;
        }

        Vehicle vehicle = vehicleRepository.findById(vehicleId);
        boolean deleteVehicle = vehicleRepository.deleteById(vehicleId);
        if (deleteVehicle) {
            result.setPayload(vehicle);
        }
        return result;
    }

    private Result<Vehicle> validate(Vehicle vehicle) {
        Result<Vehicle> result = new Result<>();

        if(vehicle == null) {
            result.addMessage("Vehicle cannot be null.", ResultType.INVALID);
            return result;
        }

        if(Validations.isNullOrBlank(vehicle.getMake())) {
            result.addMessage("Make is required.", ResultType.INVALID);
        }

        if(Validations.isNullOrBlank(vehicle.getModel())) {
            result.addMessage("Model is required.", ResultType.INVALID);
        }

        int year = vehicle.getYear();
        String yearStr = String.valueOf(year);

        if (yearStr.length() != 4 || year < 2020 || year > 2026) {
            result.addMessage("Year must be 4 digits and between 2020 and 2026.", ResultType.INVALID);
        }

        if (vehicle.getRentRate() != null && vehicle.getRentRate().signum() < 0) {
            result.addMessage("Rent rate cannot be negative.", ResultType.INVALID);
        }

        if (vehicle.getLeaseRate() != null && vehicle.getLeaseRate().signum() < 0) {
            result.addMessage("Lease rate cannot be negative.", ResultType.INVALID);
        }

        if (vehicle.getDealershipId() <= 0) {
            result.addMessage("Dealership ID is required.", ResultType.INVALID);
        }

        if (result.isSuccess()) {
            result.setPayload(vehicle);
        }

        return result;

    }

}
