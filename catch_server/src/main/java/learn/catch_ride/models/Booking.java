package learn.catch_ride.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private int bookingId;
    private int vehicleId;
    private int userId;
    private int dealershipLocationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate dateCreated;
    private BigDecimal total;
    private BookingType bookingType;

    public Booking() {

    }

    public Booking(int bookingId, int vehicleId, int userId, int dealershipLocationId, LocalDate startDate, LocalDate endDate, LocalDate dateCreated, BigDecimal total, BookingType bookingType) {
        this.bookingId = bookingId;
        this.vehicleId = vehicleId;
        this.userId = userId;
        this.dealershipLocationId = dealershipLocationId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dateCreated = dateCreated;
        this.total = total;
        this.bookingType = bookingType;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getDealershipLocationId() {
        return dealershipLocationId;
    }

    public void setDealershipLocationId(int dealershipLocationId) {
        this.dealershipLocationId = dealershipLocationId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public BookingType getBookingType() {
        return bookingType;
    }

    public void setBookingType(BookingType bookingType) {
        this.bookingType = bookingType;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return vehicleId == booking.vehicleId &&
                dealershipLocationId == booking.dealershipLocationId &&
                Objects.equals(startDate, booking.startDate) &&
                Objects.equals(endDate, booking.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vehicleId, dealershipLocationId, startDate, endDate);
    }
}
