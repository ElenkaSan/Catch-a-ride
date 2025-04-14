package learn.catch_ride.models;

import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private int dealershipId;
    private String name;
    private String description;
    private int locationId;
    private List<Vehicle> cars = new ArrayList<>();

    public Dealership(int dealershipId, String name, String description, int locationId) {
        this.dealershipId = dealershipId;
        this.name = name;
        this.description = description;
        this.locationId = locationId;
    }

    public Dealership(){

    }

    public int getDealershipId() {
        return dealershipId;
    }

    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public List<Vehicle> getCars() {
        return cars;
    }

    public void setCars(List<Vehicle> cars) {
        this.cars = cars;
    }

}
