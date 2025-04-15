package learn.catch_ride.domain;


import learn.catch_ride.data.LocationRepository;
import learn.catch_ride.data.UserRepository;
import learn.catch_ride.models.Location;
import learn.catch_ride.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public List<User> findAll() { return userRepository.findAll(); }

    public User findById(int userId) { return userRepository.findById(userId); }

    public Result<User> add(User user) {
        Result<User> result = validate(user);
        if (!result.isSuccess()) {
            return result;
        }


        user = userRepository.add(user);
        result.setPayload(user);
        return result;
    }

    public Result<User> update(User user) {
        Result<User> result = validate(user);
        if (!result.isSuccess()) {
            return result;
        }

        userRepository.update(user);

        result.setPayload(user);

        return result;
    }

    public Result<User> deleteById(int userId) {
        Result<User> result = new Result<>();
        User user = findById(userId);


        userRepository.deleteById(userId);

        result.setPayload(user);

        return result;
    }


    private Result<User> validate(User user) {
        Result<User> result = new Result<>();
        List<User> users = userRepository.findAll();

        if (user == null) {
            result.addMessage("user cannot be null.", ResultType.INVALID);
        }

        if (user.getFirstName().isEmpty() || user.getFirstName() ==  null) {
            result.addMessage("First Name is required.", ResultType.INVALID);
        }

        if (user.getLastName().isEmpty() || user.getLastName() ==  null) {
            result.addMessage("Last Name is required.", ResultType.INVALID);
        }

        if (user.getEmail().isEmpty() || user.getEmail() ==  null) {
            result.addMessage("Email is required.", ResultType.INVALID);
        }

        //TO-DO password Validation

        //TO-DO locationId Validation



        for(User u: users) {
            if(u.getUserId() == user.getUserId()){
                continue;
            }
            if(u.getEmail().equals(user.getEmail())) {
                result.addMessage("Cannot have duplicate users.", ResultType.INVALID);
                break;
            }
        }

        return result;

    }
}
