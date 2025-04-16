package learn.catch_ride.models;

import java.time.LocalDate;
import java.util.Objects;

public class User {
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateCreatedAt;
    private int locationId;
    private int appUserId;

    public int getAppUserId() {
        return appUserId;
    }

    public void setAppUserId(int appUserId) {
        this.appUserId = appUserId;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }



    public LocalDate getDateCreatedAt() {
        return dateCreatedAt;
    }

    public void setDateCreatedAt(LocalDate dateCreatedAt) {
        this.dateCreatedAt = dateCreatedAt;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }



    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private Location location;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }
}
