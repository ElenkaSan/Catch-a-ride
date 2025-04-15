package learn.catch_ride.data;

import learn.catch_ride.models.Dealership;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class DealershipJdbcTemplateRepositoryTest {
    final static int NEXT_ID = 3;

    @Autowired
    DealershipJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void shouldFindById() {
        Dealership dealership = repository.findById(2);
        assertNotNull(dealership);
        assertEquals("Chucklemotor", dealership.getName());
        assertEquals("Serving people since 1980", dealership.getDescription());
        assertEquals(1, dealership.getLocationId());
    }

    @Test
    void findByName() {
        List<Dealership> dealership = repository.findByName("Autoloco");
        assertNotNull(dealership);
        assertTrue(dealership.size() >= 1);
    }

    @Test
    void shouldFindAll() {
        List<Dealership> dealership = repository.findAll();
        assertNotNull(dealership);
        assertTrue(dealership.size() >= 2);
    }

    @Test
    void shouldAddDealership() {
        Dealership dealership = makeDealership();
        Dealership actual = repository.add(dealership);
        assertNotNull(actual);
        assertEquals(NEXT_ID, actual.getDealershipId());
    }

    @Test
    void shouldUpdateDealership() {
        Dealership dealership = makeDealership();
        Dealership added = repository.add(dealership);
        added.setName("Updated Name");
        assertTrue(repository.update(added));
    }

    @Test
    void deleteById() {
        assertTrue(repository.deleteById(2));
        assertFalse(repository.deleteById(2));
    }

    private Dealership makeDealership() {
        Dealership dealership = new Dealership();
        dealership.setName("Caralin");
        dealership.setDescription("Some info");
        dealership.setLocationId(1);
        return dealership;
    }
}