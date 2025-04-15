package learn.catch_ride.data;

import learn.catch_ride.models.Vehicle;

import java.util.List;

public interface VehicleRepository {
    Vehicle findById(int vehicleId);
    List<Vehicle> findAll();
    Vehicle add(Vehicle vehicle);
    boolean update(Vehicle vehicle);
    boolean deleteById(int vehicleId);
    List<Vehicle> findByDealershipId(int dealershipId);
}
