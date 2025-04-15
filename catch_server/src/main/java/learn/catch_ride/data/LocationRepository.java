package learn.catch_ride.data;
import java.util.List;

import learn.catch_ride.models.Location;
import org.springframework.transaction.annotation.Transactional;



public interface LocationRepository {
    List<Location> findAll();

    Location findById(int locationId);

    Location add(Location location);

    boolean update(Location location);

    @Transactional
    boolean deleteById(int locationId);

    int getUsageCount(int locationId);
}
