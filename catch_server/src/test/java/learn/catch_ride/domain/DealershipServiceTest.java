package learn.catch_ride.domain;

import learn.catch_ride.data.BookingRepository;
import learn.catch_ride.data.DealershipRepository;
import learn.catch_ride.data.UserRepository;
import learn.catch_ride.data.VehicleRepository;
import learn.catch_ride.models.Booking;
import learn.catch_ride.models.Dealership;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DealershipServiceTest {

    @Autowired
    DealershipService service;

    @MockBean
    DealershipRepository dealershipRepository;
    @MockBean
    VehicleRepository vehicleRepository;
    @MockBean
    UserRepository userRepository;

    @Test
    void shouldNotAddWhenNull() {
        Result<Dealership> result = service.add(null);
        assertFalse(result.isSuccess());
    }


    @Test
    void findByName() {
    }

    @Test
    void findByLocationId() {
    }

    @Test
    void findById() {
    }

    @Test
    void add() {
    }

    @Test
    void update() {
    }

    @Test
    void deleteById() {
    }

    private Dealership makeDealership() {
        Dealership dealership = new Dealership();
        dealership.setName("Caralin");
        dealership.setDescription("Some info");
        dealership.setLocationId(1);
        return dealership;
    }
}