package learn.catch_ride.models;

public enum BookingType {
    RENT("RENT"),
    LEASE("LEASE");

    private final String abbreviation;

    BookingType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}

