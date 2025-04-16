package learn.catch_ride.data;

import learn.catch_ride.models.AppUser;
import org.springframework.transaction.annotation.Transactional;

public interface AppUserRepository {
    @Transactional
    AppUser findByUsername(String username);

    @Transactional
    AppUser create(AppUser user);

    @Transactional
    void update(AppUser user);
}
