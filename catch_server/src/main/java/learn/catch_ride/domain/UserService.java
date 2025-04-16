package learn.catch_ride.domain;


import learn.catch_ride.data.LocationRepository;
import learn.catch_ride.data.UserRepository;
import learn.catch_ride.models.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final LocationRepository locationRepository;


    public UserService(UserRepository userRepository, LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }

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

        if (userRepository.getUsageCount(userId) > 0) {
            result.addMessage("Cannot delete location that is referenced in other tables.", ResultType.INVALID);
            result.setPayload(user);
            return result;
        }


        userRepository.deleteById(userId);

        result.setPayload(user);

        return result;
    }


    private Result<User> validate(User user) {
        Result<User> result = new Result<>();
        List<User> users = userRepository.findAll();

        if (user == null) {
            result.addMessage("user cannot be null.", ResultType.INVALID);
            return result;
        }

        if (Validations.isNullOrBlank(user.getFirstName())) {
            result.addMessage("First Name is required.", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(user.getLastName())) {
            result.addMessage("Last Name is required.", ResultType.INVALID);
        }

        if (Validations.isNullOrBlank(user.getEmail())) {
            result.addMessage("Email is required.", ResultType.INVALID);
        }


        if (locationRepository.findById(user.getLocationId()) == null) {
            result.addMessage("Location not found in database.", ResultType.INVALID);
        }


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
