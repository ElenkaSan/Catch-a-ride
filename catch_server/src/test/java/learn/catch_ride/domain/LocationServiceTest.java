package learn.catch_ride.domain;

import learn.catch_ride.data.LocationRepository;
import learn.catch_ride.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class LocationServiceTest {

    @Autowired
    LocationService service;

    @MockBean
    LocationRepository repository;


    @Test
    void shouldNotFindBooking() {
        assertNull(service.findById(999));
    }

    @Test
    void shouldAddWhenValid(){
        Location location = makeLocation();
        Result<Location> result = service.add(location);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddWhenNull() {
        Result<Location> result = service.add(null);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullAddress() {
        Location location = makeLocation();
        location.setAddress(null);

        Result<Location> result = service.add(location);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullCity() {
        Location location = makeLocation();
        location.setCity(null);

        Result<Location> result = service.add(location);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullState() {
        Location location = makeLocation();
        location.setState(null);

        Result<Location> result = service.add(location);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullZipCode() {
        Location location = makeLocation();
        location.setZipCode(0);

        Result<Location> result = service.add(location);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldUpdateWhenValid(){
        Location location = makeLocation();
        Result<Location> result = service.add(location);

        assertTrue(result.isSuccess());

        location.setCity("test");
        Result<Location> updated = service.update(location);
        assertTrue(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWhenNull() {
        Location location = makeLocation();
        Result<Location> result = service.add(location);

        assertTrue(result.isSuccess());

        location = null;
        Result<Location> updated = service.update(location);
        assertFalse(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullAddress() {
        Location location = makeLocation();
        Result<Location> result = service.add(location);

        assertTrue(result.isSuccess());

        location.setAddress(null);
        Result<Location> updated = service.update(location);
        assertFalse(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullCity() {
        Location location = makeLocation();
        Result<Location> result = service.add(location);

        assertTrue(result.isSuccess());

        location.setCity(null);
        Result<Location> updated = service.update(location);
        assertFalse(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullState() {
        Location location = makeLocation();
        Result<Location> result = service.add(location);

        assertTrue(result.isSuccess());

        location.setState(null);
        Result<Location> updated = service.update(location);
        assertFalse(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullZipCode() {
        Location location = makeLocation();
        Result<Location> result = service.add(location);

        assertTrue(result.isSuccess());

        location.setZipCode(0);
        Result<Location> updated = service.update(location);
        assertFalse(updated.isSuccess());
    }


    @Test
    void shouldDelete() {
        Location location = makeLocation();
        Result<Location> result = service.add(location);

        assertTrue(result.isSuccess());

        Result<Location> updated = service.deleteById(location.getLocationId());
        assertTrue(updated.isSuccess());
    }



    Location makeLocation() {
        //(1, '18 Washington Ave', 'Boston','MA', 20235),
        Location location = new Location();
        location.setLocationId(1);
        location.setAddress("18 Washington Ave");
        location.setCity("Boston");
        location.setState("Ma");
        location.setZipCode(20235);
        return location;
    }
}