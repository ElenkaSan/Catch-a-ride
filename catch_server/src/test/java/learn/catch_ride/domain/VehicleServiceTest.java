package learn.catch_ride.domain;

import learn.catch_ride.data.BookingRepository;
import learn.catch_ride.data.DealershipRepository;
import learn.catch_ride.data.VehicleRepository;
import learn.catch_ride.models.Vehicle;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class VehicleServiceTest {

    @Autowired
    VehicleService service;

    @MockBean
    VehicleRepository vehicleRepository;
    @MockBean
    BookingRepository bookingRepository;
    @MockBean
    DealershipRepository dealershipRepository;

    @Test
    void shouldNotAddNull() {
        Result<Vehicle> result = service.add(null);
        System.out.println(result.getMessages());
        assertFalse(result.isSuccess());
        assertFalse(result.isSuccess());
        assertEquals(ResultType.INVALID, result.getType());
    }

    @Test
    void shouldNotAddIfMissingRequired() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("");
        vehicle.setModel(null);
        when(vehicleRepository.findById(1)).thenReturn(new Vehicle());
        Result<Vehicle> result = service.add(vehicle);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Make is required."));
        assertTrue(result.getMessages().contains("Model is required."));
    }

    @Test
    void shouldNotAddVehicleWithInvalidYearLength() {
        Vehicle vehicle = new Vehicle();
        vehicle.setYear(23);
        vehicle.setBookingStatus(true);

        Result<Vehicle> result = service.add(vehicle);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Year must be 4 digits and between 2020 and 2026."));
    }
    @Test
    void shouldNotAddInvalidYear() {
        Vehicle vehicle = makeVehicle();
        vehicle.setYear(2010);
        vehicle.setBookingStatus(true);

        Result<Vehicle> result = service.add(vehicle);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Year must be 4 digits and between 2020 and 2026."));
    }

    @Test
    void shouldNotAddNegativeRate() {
        Vehicle vehicle = makeVehicle();
        vehicle.setRentRate(new BigDecimal("-30.00"));
        vehicle.setLeaseRate(new BigDecimal("-250.00"));
        vehicle.setBookingStatus(true);

        Result<Vehicle> result = service.add(vehicle);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Rent rate cannot be negative."));
        assertTrue(result.getMessages().contains("Lease rate cannot be negative."));
    }

    @Test
    void shouldNotAddMissingDealershipId(){
        Vehicle vehicle = makeVehicle();
        vehicle.setDealershipId(0);
        vehicle.setBookingStatus(true);

        Result<Vehicle> result = service.add(vehicle);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Dealership ID is required."));
    }

    @Test
    void shouldAddValidVehicle() {
        Vehicle vehicle = makeVehicle();
        when(vehicleRepository.add(vehicle)).thenReturn(vehicle);
        Result<Vehicle> result = service.add(vehicle);

        assertTrue(result.isSuccess());
        assertEquals(vehicle, result.getPayload());
    }

    @Test
    void shouldNotUpdateIfMissingRequired() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("");
        vehicle.setModel(null);
        when(vehicleRepository.findById(1)).thenReturn(new Vehicle());
        Result<Vehicle> result = service.update(vehicle);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Make is required."));
        assertTrue(result.getMessages().contains("Model is required."));
    }

    @Test
    void shouldNotUpdateInvalidYear() {
        Vehicle vehicle = makeVehicle();
        vehicle.setVehicleId(1);
        vehicle.setYear(2015);
        vehicle.setBookingStatus(true);

        when(vehicleRepository.findById(1)).thenReturn(vehicle);
        Result<Vehicle> result = service.update(vehicle);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Year must be 4 digits and between 2020 and 2026."));
    }

    @Test
    void shouldNotUpdateNegativeRate() {
        Vehicle vehicle = makeVehicle();
        vehicle.setVehicleId(1);
        vehicle.setRentRate(new BigDecimal("-30"));
        vehicle.setLeaseRate(new BigDecimal("-250"));
        vehicle.setBookingStatus(true);

        when(vehicleRepository.findById(1)).thenReturn(vehicle);
        Result<Vehicle> result = service.update(vehicle);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Rent rate cannot be negative."));
        assertTrue(result.getMessages().contains("Lease rate cannot be negative."));
    }

    @Test
    void shouldNotUpdateMissingDealershipId(){
        Vehicle vehicle = makeVehicle();
        vehicle.setVehicleId(1);
        vehicle.setDealershipId(0);
        vehicle.setBookingStatus(true);

        when(vehicleRepository.findById(1)).thenReturn(vehicle);
        Result<Vehicle> result = service.update(vehicle);
        assertFalse(result.isSuccess());
        assertTrue(result.getMessages().contains("Dealership ID is required."));
    }

    @Test
    void shouldNotUpdateNotChangedFields() {
        Vehicle vehicle = makeVehicle();
        vehicle.setVehicleId(1);

        when(vehicleRepository.findById(1)).thenReturn(vehicle);

        Vehicle updated = makeVehicle();
        updated.setVehicleId(1);
        updated.setMake("Toyota");
        updated.setModel("Camry");
        updated.setYear(2023);
        updated.setDealershipId(12);
        updated.setColor("Green");
        updated.setRentRate(new BigDecimal("75.00"));
        updated.setLeaseRate(new BigDecimal("600.00"));
        updated.setBookingStatus(true);

        Result<Vehicle> result = service.update(updated);
        System.out.println(result.getMessages());
        assertFalse(result.isSuccess());
        System.out.println(result.getMessages());
        assertTrue(result.getMessages().contains("Make, model, year, and dealership cannot be changed."));
    }

    @Test
    void shouldDeleteById() {
        Vehicle vehicle = makeVehicle();
        Result<Vehicle> result = service.add(vehicle);
        assertTrue(result.isSuccess());

        Result<Vehicle> deleted = service.deleteById(1);
        assertTrue(deleted.isSuccess());
    }

    private Vehicle makeVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setVehicleId(4);
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