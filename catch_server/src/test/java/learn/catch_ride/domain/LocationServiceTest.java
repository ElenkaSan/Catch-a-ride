package learn.catch_ride.domain;

import learn.catch_ride.data.LocationRepository;
import learn.catch_ride.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LocationServiceTest {

    @Autowired
    LocationService service;

    @MockBean
    LocationRepository repository;


    @Test
    void shouldFindAll() {
        List<Location> locations = repository.findAll();


    }



    Location makeLocation() {
        //(1, '18 Washington Ave', 'Boston','MA', 20235),
        Location location = new Location();
        location.setLocationId(1);
        location.setAddress("18 Washington Ave");
        location.setCity("Boston");
        location.setState("MA");
        location.setZipCode(20235);
        return location;
    }
}