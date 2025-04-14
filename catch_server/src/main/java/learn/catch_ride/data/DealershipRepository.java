package learn.catch_ride.data;

import learn.catch_ride.models.Dealership;

import java.util.List;

public interface DealershipRepository {
    Dealership findById(int dealershipId);

    List<Dealership> findAll();

    Dealership add(Dealership dealership);

    boolean update(Dealership dealership);

    boolean deleteById(int dealershipId);
}
