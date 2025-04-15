package learn.catch_ride.models;

import java.math.BigDecimal;

public class Vehicle {
    private int vehicleId;
    private String make;
    private String model;
    private int year;
    private String color;
    private String trim;
    private BigDecimal rentRate;
    private BigDecimal leaseRate;
    private int dealershipId;
    private boolean bookingStatus;

    public Vehicle(int vehicleId, String make, String model, int year, String color, String trim, BigDecimal rentRate, BigDecimal leaseRate, int dealershipId, boolean bookingStatus) {
        this.vehicleId = vehicleId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.trim = trim;
        this.rentRate = rentRate;
        this.leaseRate = leaseRate;
        this.dealershipId = dealershipId;
        this.bookingStatus = bookingStatus;
    }

    public Vehicle() {
    }

    public int getVehichleId() {
        return vehicleId;
    }

    public void setVehichleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTrim() {
        return trim;
    }

    public void setTrim(String trim) {
        this.trim = trim;
    }

    public int getDealershipId() {
        return dealershipId;
    }

    public void setDealershipId(int dealershipId) {
        this.dealershipId = dealershipId;
    }

    public BigDecimal getRentRate() {
        return rentRate;
    }

    public void setRentRate(BigDecimal rentRate) {
        this.rentRate = rentRate;
    }

    public BigDecimal getLeaseRate() {
        return leaseRate;
    }

    public void setLeaseRate(BigDecimal leaseRate) {
        this.leaseRate = leaseRate;
    }

    public boolean isBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(boolean bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
