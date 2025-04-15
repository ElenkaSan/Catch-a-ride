package learn.catch_ride.domain;

import learn.catch_ride.data.DealershipRepository;
import learn.catch_ride.data.UserRepository;
import learn.catch_ride.data.VehicleRepository;
import learn.catch_ride.models.Vehicle;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class VehicleServiceTest {

    @Autowired
    VehicleService service;

    @MockBean
    VehicleRepository vehicleRepository;
    @MockBean
    DealershipRepository dealershipRepository;
    @MockBean
    UserRepository userRepository;

    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByDealershipId() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void shouldNotUpdateNotChangedFields() {
        Vehicle vehicle = makeVehicle();
        Vehicle added = vehicleRepository.add(vehicle);

        // Attempt to modify immutable fields
        added.setMake("Toyota");
        added.setModel("Camry");
        added.setYear(2030);
        added.setDealershipId(999); // assuming this ID is different

        // Only allow updating mutable fields
        added.setColor("Green");
        added.setRentRate(new BigDecimal("75.00"));
        added.setLeaseRate(new BigDecimal("600.00"));
        added.setBookingStatus(true);

        assertTrue(vehicleRepository.update(added));

        // Re-fetch the vehicle from DB to verify the update behavior
        Vehicle actual = vehicleRepository.findById(added.getVehichleId());
        assertNotNull(actual);

        // Immutable fields should remain unchanged
        assertEquals("Mazda", actual.getMake());
        assertEquals("Some info", actual.getModel());
        assertEquals(2025, actual.getYear());
        assertEquals(1, actual.getDealershipId());

        // Mutable fields should reflect the update
        assertEquals("Green", actual.getColor());
        assertEquals(new BigDecimal("75.00"), actual.getRentRate());
        assertEquals(new BigDecimal("600.00"), actual.getLeaseRate());
        assertTrue(actual.isBookingStatus());
    }



    @Test
    void deleteById() {
    }

    private Vehicle makeVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Mazda");
        vehicle.setModel("Some info");
        vehicle.setYear(2025);
        vehicle.setColor("Teal");
        vehicle.setTrim("Sport");
        vehicle.setRentRate(new BigDecimal(30.00));
        vehicle.setLeaseRate(new BigDecimal(250.00));
        vehicle.setDealershipId(1);
        vehicle.setBookingStatus(false);
        return vehicle;
    }
}