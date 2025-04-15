package learn.catch_ride.domain;

import learn.catch_ride.data.*;
import learn.catch_ride.models.Booking;
import learn.catch_ride.models.Dealership;
import learn.catch_ride.models.Location;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DealershipServiceTest {

    @Autowired
    DealershipService service;

    @MockBean
    DealershipRepository dealershipRepository;
    @MockBean
    LocationRepository locationRepository;

    @Test
    void shouldNotAddWhenNull() {
        Result<Dealership> result = service.add(null);
        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
        assertTrue(result.getMessages().contains("Dealership cannot be null"));
    }

    @Test
    void shouldNotAddWithoutName() {
        Dealership dealership = new Dealership();
        dealership.setLocationId(1);
        when(locationRepository.findById(1)).thenReturn(new Location());
        Result<Dealership> result = service.add(dealership);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Name is required"));
    }

    @Test
    void shouldAdd() {
        Dealership dealership = new Dealership(0, "Name one", "Some info", 1);
        Dealership mockOut = new Dealership(4, "Name two", "some some", 1);
        when(locationRepository.findById(1)).thenReturn(new Location());
        when(dealershipRepository.add(dealership)).thenReturn(mockOut);
        Result<Dealership> actual = service.add(dealership);
        assertEquals(ResultType.SUCCESS, actual.getType());
        assertEquals(mockOut, actual.getPayload());
    }

    @Test
    void shouldNotUpdateMissingName() {
        Dealership dealership = new Dealership();
        dealership.setDealershipId(1);
        dealership.setLocationId(1);
        when(locationRepository.findById(1)).thenReturn(new Location());

        Result<Dealership> result = service.update(dealership);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Name is required"));
    }

    @Test
    void shouldNotAddDealershipIfMissingLocation() {
        Dealership dealership = new Dealership();
        dealership.setName("Missing Location");
        Result<Dealership> result = service.add(dealership);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Location ID is required"));
    }

    @Test
    void shouldNotAddDealershipIfLocationDoesNotExist() {
        Dealership dealership = new Dealership();
        dealership.setName("Some Location");
        dealership.setLocationId(42);
        when(locationRepository.findById(42)).thenReturn(null);
        Result<Dealership> result = service.add(dealership);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Location must exist"));
    }

}