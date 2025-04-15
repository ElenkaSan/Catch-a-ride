package learn.catch_ride.models;

import java.util.Objects;

public class Location {
    private int locationId;
    private String address;
    private String city;
    private String state;
    private int zipCode;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return zipCode == location.zipCode && Objects.equals(address, location.address) && Objects.equals(city, location.city) && Objects.equals(state, location.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, city, state, zipCode);
    }
}
