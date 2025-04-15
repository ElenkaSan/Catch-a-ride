package learn.catch_ride.data;

import learn.catch_ride.models.Location;
import learn.catch_ride.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJdbcTemplateRepositoryTest {

    final static int NEXT_ID = 3;

    @Autowired
    UserJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup() { knownGoodState.set(); }

    @Test
    void findAll() {
        List<User> users = repository.findAll();
        assertNotNull(users);

        assertTrue(users.size() >= 2);
    }

    @Test
    void shouldFindById() {
        User user = repository.findById(1);
        assertNotNull(user);

        assertEquals(1, user.getUserId());
        assertEquals("username1", user.getUserName());
        assertEquals("James", user.getFirstName());
        assertEquals("Sauven", user.getLastName());
        assertEquals("jamessauven@gmail.com", user.getEmail());
        assertEquals("testpassword",user.getPassword());
        assertEquals(LocalDate.of(2017,12,20),user.getDateCreatedAt());
        assertEquals(1,user.getLocationId());
        assertFalse(user.isAdmin());
    }



    @Test
    void shouldAdd() {
        User user = makeUser();
        User actual = repository.add(user);
        assertNotNull(actual);
        assertEquals(NEXT_ID, user.getUserId());
        assertEquals("username3", user.getUserName());
        assertEquals("testFirstName", user.getFirstName());
        assertEquals("testLastName", user.getLastName());
        assertEquals("testEmail@email.com", user.getEmail());
        assertEquals("testPassword",user.getPassword());
        assertEquals(LocalDate.now(),user.getDateCreatedAt());
        assertEquals(1,user.getLocationId());
        assertFalse(user.isAdmin());
    }

    @Test
    void shouldUpdate() {
        User user = makeUser();
        user.setEmail("testEmail1@email.com");
        user.setUserId(2);
        assertTrue(repository.update(user));
        user.setUserId(12);
        assertFalse(repository.update(user));
    }

    @Test
    void shouldDeleteById() {
        User user = makeUser();
        user.setEmail("testEmail2@email.com");
        User actual = repository.add(user);
        assertNotNull(actual);
        assertTrue(repository.deleteById(3));
        assertFalse(repository.deleteById(3));
    }

    private User makeUser() {
        User user = new User();
        user.setUserName("username3");
        user.setFirstName("testFirstName");
        user.setLastName("testLastName");
        user.setEmail("testEmail@email.com");
        user.setPassword("testPassword");
        user.setDateCreatedAt(LocalDate.now());
        user.setLocationId(1);
        user.setAdmin(false);
        return user;
    }
}