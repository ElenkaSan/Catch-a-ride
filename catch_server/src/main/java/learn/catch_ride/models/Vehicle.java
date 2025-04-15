package learn.catch_ride.models;

import java.math.BigDecimal;

public class Vehicle {
    private int vehichleId;
    private String make;
    private String model;
    private int year;
    private String color;
    private String trim;
    private int dealershipId;
    private BigDecimal rentRate;
    private BigDecimal leaseRate;

    public Vehicle(int vehichleId, String make, String model, int year, String color, String trim, int dealershipId, BigDecimal rentRate, BigDecimal leaseRate) {
        this.vehichleId = vehichleId;
        this.make = make;
        this.model = model;
        this.year = year;
        this.color = color;
        this.trim = trim;
        this.dealershipId = dealershipId;
        this.rentRate = rentRate;
        this.leaseRate = leaseRate;
    }

    public Vehicle() {
    }

    public int getVehichleId() {
        return vehichleId;
    }

    public void setVehichleId(int vehichleId) {
        this.vehichleId = vehichleId;
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
}
