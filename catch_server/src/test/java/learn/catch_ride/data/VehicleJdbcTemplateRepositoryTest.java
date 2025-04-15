package learn.catch_ride.data;

import learn.catch_ride.models.Booking;
import learn.catch_ride.models.Dealership;
import learn.catch_ride.models.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class VehicleJdbcTemplateRepositoryTest {
    final static int NEXT_ID = 5;

    @Autowired
    VehicleJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindById() {
        Vehicle vehicle = repository.findById(1);
        assertNotNull(vehicle);
        assertEquals("Ford", vehicle.getMake());
        assertEquals("Maverick", vehicle.getModel());
        assertEquals(2021, vehicle.getYear());
        assertEquals("Blue", vehicle.getColor());
        assertEquals("Sport", vehicle.getTrim());
        assertEquals(new BigDecimal("60"), vehicle.getRentRate());
        assertEquals(new BigDecimal("480"), vehicle.getLeaseRate());
        assertEquals(1, vehicle.getDealershipId());
    }

    @Test
    void shouldFindAll() {
        List<Vehicle> vehicle = repository.findAll();
        assertNotNull(vehicle);
        assertTrue(vehicle.size() >= 0);
    }

    @Test
    void shouldAddVehicle() {
        Vehicle vehicle = makeVehicle();
        Vehicle actual = repository.add(vehicle);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getVehichleId());
    }

    @Test
    void shouldUpdateVehicle() {
        Vehicle vehicle = makeVehicle();
        Vehicle added = repository.add(vehicle);
        added.setColor("Red");
        added.setBookingStatus(true);
        added.setRentRate(new BigDecimal(90.00));
        added.setLeaseRate(new BigDecimal(550.00));
        assertTrue(repository.update(added));
    }

    @Test
    void shouldDeleteByIdVehicle() {
        Vehicle vehicle = makeVehicle(); // Helper method to set fields
        vehicle = repository.add(vehicle);
        assertNotNull(vehicle);
        boolean success = repository.deleteById(vehicle.getVehichleId());
        assertTrue(success);
    }

    @Test
    void shouldFindByDealershipId() {
        List<Vehicle> dealership = repository.findByDealershipId(1);
        assertNotNull(dealership);
        assertEquals(1, dealership.size());
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