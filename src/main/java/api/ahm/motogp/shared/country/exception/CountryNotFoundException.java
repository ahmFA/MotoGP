package api.ahm.motogp.shared.country.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Integer id) {
        super("Country ID: " + id + " not found");
    }
}
