package learn.catch_ride.data;

import learn.catch_ride.models.Booking;
import learn.catch_ride.models.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static learn.catch_ride.models.BookingType.LEASE;
import static learn.catch_ride.models.BookingType.RENT;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class LocationJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 3;

    @Autowired
    LocationJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void findAll() {
        List<Location> locations = repository.findAll();
        assertNotNull(locations);

        assertTrue(locations.size() >= 2);
    }

    @Test
    void shouldFindById() {
        Location location = repository.findById(1);
        assertNotNull(location);

        assertEquals(1, location.getLocationId());
        assertEquals("18 Washington Ave", location.getAddress());
        assertEquals("Boston", location.getCity());
        assertEquals("MA", location.getState());
        assertEquals(20235, location.getZipCode());
    }



    @Test
    void shouldAdd() {
        Location location = makeLocation();
        Location actual = repository.add(location);
        assertNotNull(actual);
        assertEquals(NEXT_ID, location.getLocationId());
        assertEquals("test address", location.getAddress());
        assertEquals("test city", location.getCity());
        assertEquals("testState", location.getState());
        assertEquals(11111, location.getZipCode());
    }

    @Test
    void shouldUpdate() {
        Location location = makeLocation();
        location.setLocationId(2);
        assertTrue(repository.update(location));
        location.setLocationId(13);
        assertFalse(repository.update(location));
    }

    @Test
    void shouldDeleteById() {
        Location location = makeLocation();
        Location actual = repository.add(location);
        assertNotNull(actual);
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(3));
    }

    private Location makeLocation() {
        Location location = new Location();
        location.setAddress("test address");
        location.setCity("test city");
        location.setState("testState");
        location.setZipCode(11111);
        return location;
    }
}