package learn.catch_ride.domain;

import learn.catch_ride.data.DealershipRepository;
import learn.catch_ride.data.LocationRepository;
import learn.catch_ride.data.VehicleRepository;
import learn.catch_ride.models.Dealership;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DealershipService {
    private final DealershipRepository dealershipRepository;
    private final LocationRepository locationRepository;

    public DealershipService(DealershipRepository dealershipRepository, LocationRepository locationRepository) {
        this.dealershipRepository = dealershipRepository;
        this.locationRepository = locationRepository;
    }

    public List<Dealership> findAll() { return dealershipRepository.findAll(); }

    public List<Dealership> findByName(String name) { return dealershipRepository.findByName(name); }

    public List<Dealership> findByLocationId(int locationId) { return dealershipRepository.findByLocationId(locationId); }

    public Dealership findById(int dealershipId) { return dealershipRepository.findById(dealershipId); }

    //crud

    public Result<Dealership> add(Dealership dealership){
        Result<Dealership> result = validate(dealership);
        if(!result.isSuccess()) {
            return result;
        }

        if(dealership.getDealershipId() != 0){
            result.addMessage("Dealership ID cannot be set for `add` operation", ResultType.INVALID);
            return result;
        }

        dealership = dealershipRepository.add(dealership);
        result.setPayload(dealership);
        return result;

    }

   public Result<Dealership> update(Dealership dealership){
        Result<Dealership> result = validate(dealership);
        if(!result.isSuccess()) {
            return result;
        }

        if(dealership.getDealershipId() <= 0){
            result.addMessage("Dealership ID must be set for `update` operation", ResultType.INVALID);
            return result;
        }

       if (!dealershipRepository.update(dealership)){
           String msg = String.format("DealershipId: %s, not found",
                   dealership.getDealershipId());
           result.addMessage(msg, ResultType.NOT_FOUND);
       }
       return result;

   }

    public Result<Dealership> deleteById(int dealershipId){
        Result<Dealership> result = new Result<>();
        Dealership dealership = findById(dealershipId);

        if (dealership.getCars() != null && !dealership.getCars().isEmpty()){
            String message = String.format("Dealership ID: %s is in use by vehicles and cannot be deleted.", dealershipId);
            result.addMessage(message, ResultType.INVALID);
            return result;
        }

        dealershipRepository.deleteById(dealershipId);
        result.setPayload(dealership);
        return result;
    }

    private Result<Dealership> validate(Dealership dealership){
        Result<Dealership> result = new Result<>();

        if(dealership == null){
            result.addMessage("Dealership cannot be null", ResultType.INVALID);
            return result;
        }

        if(Validations.isNullOrBlank(dealership.getName())){
            result.addMessage("Name is required", ResultType.INVALID);
        }

        if(dealership.getLocationId() == 0){
            result.addMessage("Location ID is required", ResultType.INVALID);
        } else if(locationRepository.findById(dealership.getLocationId()) == null){
            result.addMessage("Location must exist", ResultType.INVALID);
        }

        return result;
    }
}
