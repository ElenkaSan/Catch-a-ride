package learn.catch_ride.data;

import learn.catch_ride.models.Vehicle;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface VehicleRepository {
    Vehicle findById(int vehicleId);
    List<Vehicle> findAll();
    Vehicle add(Vehicle vehicle);
    boolean update(Vehicle vehicle);
    @Transactional
    boolean deleteById(int vehicleId);
    List<Vehicle> findByDealershipId(int dealershipId);
}
