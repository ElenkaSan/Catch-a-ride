package learn.catch_ride.domain;

public class Validations {

    public static boolean isNullOrBlank(String value) {
        return value == null || value.isBlank();
    }
}