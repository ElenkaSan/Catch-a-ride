package learn.catch_ride.domain;

import learn.catch_ride.data.UserRepository;
import learn.catch_ride.models.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
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
        //(1,'James','Sauven','jamessauven@gmail.com', '2017-12-20',1,1),
        User user = new User();
        user.setUserId(1);
        user.setFirstName("James");
        user.setLastName("Sauven");
        user.setEmail("jamessauven@gmail.com");
        user.setDateCreatedAt(LocalDate.now());
        user.setLocationId(1);
        user.setAppUserId(1);
        return user;
    }
}