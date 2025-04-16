package learn.catch_ride.data;

import learn.catch_ride.models.Dealership;
import learn.catch_ride.models.Vehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class DealershipJdbcTemplateRepositoryTest {
    final static int NEXT_ID = 3;

    @Autowired
    DealershipJdbcTemplateRepository dealershipRepository;

    @Autowired
    VehicleJdbcTemplateRepository vehicleRepository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindById() {
        Dealership dealership = dealershipRepository.findById(2);
        assertNotNull(dealership);
        assertEquals("Chucklemotor", dealership.getName());
        assertEquals("Serving people since 1980", dealership.getDescription());
        assertEquals(1, dealership.getLocationId());
    }

    @Test
    void shouldFindByIdWithVehicles() {
        Dealership dealership = dealershipRepository.findById(1);
        assertNotNull(dealership);
        assertEquals("Autoloco", dealership.getName());

        List<Vehicle> cars = dealership.getCars();
        assertNotNull(cars);
        assertTrue(cars.size() > 0, "Should load cars via vehicleRepository");
    }

    @Test
    void shouldFindByName() {
        List<Dealership> dealerships = dealershipRepository.findByName("Autoloco");
        assertNotNull(dealerships);
        assertTrue(dealerships.size() >= 1);

        for (Dealership d : dealerships) {
            assertNotNull(d.getCars());
            assertTrue(d.getCars().size() > 0, "Should have cars loaded from vehicleRepository");
        }
    }

    @Test
    void shouldFindAll() {
        List<Dealership> dealership = dealershipRepository.findAll();
        assertNotNull(dealership);
        assertTrue(dealership.size() >= 2);
    }

    @Test
    void shouldAddDealership() {
        Dealership dealership = makeDealership();
        Dealership actual = dealershipRepository.add(dealership);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getDealershipId());
    }

    @Test
    void shouldUpdateDealership() {
        Dealership dealership = makeDealership();
        Dealership added = dealershipRepository.add(dealership);
        added.setName("Updated Name");
        assertTrue(dealershipRepository.update(added));
    }

    @Test
    void deleteById() {
        assertTrue(dealershipRepository.deleteById(2));
        assertFalse(dealershipRepository.deleteById(2));
    }

    private Dealership makeDealership() {
        Dealership dealership = new Dealership();
        dealership.setName("Carlin");
        dealership.setDescription("Some info");
        dealership.setLocationId(1);
        return dealership;
    }
}