package learn.catch_ride.domain;

import learn.catch_ride.data.LocationRepository;
import learn.catch_ride.data.UserRepository;
import learn.catch_ride.models.Booking;
import learn.catch_ride.models.Location;
import learn.catch_ride.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserServiceTest {

    @Autowired
    UserService service;

    @MockBean
    UserRepository repository;


    @Test
    void shouldNotFindUser() {
        assertNull(service.findById(999));
    }

    @Test
    void shouldAddWhenValid(){
        User user = makeUser();
        Result<User> result = service.add(user);

        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddWhenNull() {
        Result<User> result = service.add(null);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullUserName() {
        User user = makeUser();
        user.setUserName(null);

        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullFirstName() {
        User user = makeUser();
        user.setFirstName(null);

        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullLastName() {
        User user = makeUser();
        user.setLastName(null);

        Result<User> result = service.add(null);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullEmail() {
        User user = makeUser();
        user.setEmail(null);

        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithNullPassword() {
        User user = makeUser();
        user.setPassword(null);

        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddWithInvalidPassword() {
        User user = makeUser();
        user.setPassword("123456789");

        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());

        user.setPassword("timmyturner");

        result = service.add(user);
        assertFalse(result.isSuccess());


        user.setPassword("TimmyTurner");

        result = service.add(user);
        assertFalse(result.isSuccess());

        user.setPassword("TimmyTurner1");

        result = service.add(user);
        assertFalse(result.isSuccess());

        user.setPassword("Timm");

        result = service.add(user);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldAddValidPassword(){
        User user = makeUser();
        user.setPassword("TimmyTurner1!");

        Result<User> result = service.add(user);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotAddWhenLocationNotFound() {
        User user = makeUser();
        user.setLocationId(9999);

        Result<User> result = service.add(user);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldUpdateWhenValid(){
        User user = makeUser();
        Result<User> result = service.add(user);

        assertTrue(result.isSuccess());

        user.setFirstName("test");
        Result<User> updated = service.update(user);

        assertTrue(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWhenNull() {
        User user = makeUser();
        Result<User> result = service.add(user);

        assertTrue(result.isSuccess());

        user = null;
        Result<User> updated = service.update(user);

        assertFalse(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullUserName() {
        User user = makeUser();
        Result<User> result = service.add(user);

        assertTrue(result.isSuccess());

        user.setUserName(null);
        Result<User> updated = service.update(user);

        assertFalse(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullFirstName() {
        User user = makeUser();
        Result<User> result = service.add(user);

        assertTrue(result.isSuccess());

        user.setFirstName(null);
        Result<User> updated = service.update(user);

        assertFalse(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullLastName() {
        User user = makeUser();
        Result<User> result = service.add(user);

        assertTrue(result.isSuccess());

        user.setLastName(null);
        Result<User> updated = service.update(user);

        assertFalse(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullEmail() {
        User user = makeUser();
        Result<User> result = service.add(user);

        assertTrue(result.isSuccess());

        user.setEmail(null);
        Result<User> updated = service.update(user);

        assertFalse(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWithNullPassword() {
        User user = makeUser();
        Result<User> result = service.add(user);

        assertTrue(result.isSuccess());

        user.setPassword(null);
        Result<User> updated = service.update(user);

        assertFalse(updated.isSuccess());
    }

    @Test
    void shouldNotUpdateWithInvalidPassword() {
        User user = makeUser();
        Result<User> result = service.add(user);

        assertTrue(result.isSuccess());



        user.setPassword("123456789");

        result = service.update(user);
        assertFalse(result.isSuccess());

        user.setPassword("timmyturner");

        result = service.update(user);
        assertFalse(result.isSuccess());


        user.setPassword("TimmyTurner");

        result = service.update(user);
        assertFalse(result.isSuccess());

        user.setPassword("TimmyTurner1");

        result = service.update(user);
        assertFalse(result.isSuccess());

        user.setPassword("Timm");

        result = service.update(user);
        assertFalse(result.isSuccess());

    }

    @Test
    void shouldUpdateValidPassword(){
        User user = makeUser();
        Result<User> result = service.add(user);
        assertTrue(result.isSuccess());

        user.setPassword("TimmyTurner1!");

        result = service.update(user);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateWhenLocationNotFound() {
        User user = makeUser();
        Result<User> result = service.add(user);
        assertTrue(result.isSuccess());



        user.setLocationId(9999);
        result = service.add(user);
        assertFalse(result.isSuccess());
    }


    @Test
    void shouldDelete() {
        User user = makeUser();
        Result<User> result = service.add(user);
        assertTrue(result.isSuccess());


        Result<User> deleted = service.deleteById(1);
        assertTrue(deleted.isSuccess());
    }



    User makeUser() {
        //(1,'James','Sauven','jamessauven@gmail.com','testpassword', '2017-12-20',1,false),
        User user = new User();
        user.setUserId(1);
        user.setUserName("username");
        user.setFirstName("James");
        user.setLastName("Sauven");
        user.setEmail("jamessauven@gmail.com");
        user.setPassword("TestPassword127!");
        user.setDateCreatedAt(LocalDate.now());
        user.setLocationId(1);
        user.setAdmin(false);
        return user;
    }
}